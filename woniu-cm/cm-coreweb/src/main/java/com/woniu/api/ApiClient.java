package com.woniu.api;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;

@Slf4j
public class ApiClient {
    /**
     * RestAPI 调用器
     */
    private final RestTemplate RT = new RestTemplate();

    private HttpHeaders headers;

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        headers = headers;
    }

    public RestTemplate getRestTemplate() {
        return RT;
    }
    /**
     * 发送 get 请求
     */
    public JSONObject get(String url) {
        return getNative(url, null, null).getBody();
    }

    /**
     * 发送 get 请求
     */
    public JSONObject get(String url, JSONObject variables) {
        return getNative(url, variables, null).getBody();
    }

    /**
     * 发送 get 请求
     */
    public JSONObject get(String url, JSONObject variables, JSONObject params) {
        return getNative(url, variables, params).getBody();
    }

    /**
     * 发送 get 请求，返回原生 ResponseEntity 对象
     */
    public ResponseEntity<JSONObject> getNative(String url, JSONObject variables,
                                                       JSONObject params) {
        return request(url, HttpMethod.GET, variables, params);
    }

    /**
     * 发送 Post 请求
     */
    public JSONObject post(String url) {
        return postNative(url, null, null).getBody();
    }

    /**
     * 发送 Post 请求
     */
    public JSONObject post(String url, JSONObject params) {
        return postNative(url, null, params).getBody();
    }

    /**
     * 发送 Post 请求
     */
    public JSONObject post(String url, JSONObject variables, JSONObject params) {
        return postNative(url, variables, params).getBody();
    }

    /**
     * 发送 POST 请求，返回原生 ResponseEntity 对象
     */
    public ResponseEntity<JSONObject> postNative(String url, JSONObject variables,
                                                        JSONObject params) {
        return request(url, HttpMethod.POST, variables, params);
    }

    /**
     * 发送 put 请求
     */
    public JSONObject put(String url) {
        return putNative(url, null, null).getBody();
    }

    /**
     * 发送 put 请求
     */
    public JSONObject put(String url, JSONObject params) {
        return putNative(url, null, params).getBody();
    }

    /**
     * 发送 put 请求
     */
    public JSONObject put(String url, JSONObject variables, JSONObject params) {
        return putNative(url, variables, params).getBody();
    }

    /**
     * 发送 put 请求，返回原生 ResponseEntity 对象
     */
    public ResponseEntity<JSONObject> putNative(String url, JSONObject variables,
                                                       JSONObject params) {
        return request(url, HttpMethod.PUT, variables, params);
    }

    /**
     * 发送 delete 请求
     */
    public JSONObject delete(String url) {
        return deleteNative(url, null, null).getBody();
    }

    /**
     * 发送 delete 请求
     */
    public JSONObject delete(String url, JSONObject variables, JSONObject params) {
        return deleteNative(url, variables, params).getBody();
    }

    /**
     * 发送 delete 请求，返回原生 ResponseEntity 对象
     */
    public ResponseEntity<JSONObject> deleteNative(String url,
                                                          JSONObject variables, JSONObject params) {
        return request(url, HttpMethod.DELETE, null, variables, params, JSONObject.class);
    }

    /**
     * 发送请求
     */
    public ResponseEntity<JSONObject> request(String url, HttpMethod method,
                                                     JSONObject variables, JSONObject params) {
        return request(url, method, getHeaderApplicationJson(), variables, params,
                JSONObject.class);
    }

    /**
     * 发送请求
     * @param url 请求地址
     * @param method 请求方式
     * @param headers 请求头 可空
     * @param variables 请求url参数 可空
     * @param params 请求body参数 可空
     * @param responseType 返回类型
     * @return ResponseEntity<responseType>
     */
    public <T> ResponseEntity<T> request(String url, HttpMethod method,
                                                HttpHeaders headers, JSONObject variables, JSONObject params,
                                                Class<T> responseType) {
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("url 不能为空");
        }
        if (method == null) {
            throw new RuntimeException("method 不能为空");
        }
        if (headers == null) {
            headers = new HttpHeaders();
        }
        // 请求体
        String body = "";
        if (params != null) {
            body = params.toJSONString();
        }
        // 拼接 url 参数
        if (variables != null) {
            url += ("?" + asUrlVariables(variables));
        }
        // 发送请求
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        return RT.exchange(url, method, request, responseType);
    }

    /**
     * 获取JSON请求头
     */
    private HttpHeaders getHeaderApplicationJson() {
        return getHeader();
    }

    /**
     * 获取请求头
     */
    private HttpHeaders createHeader(String mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mediaType));
        headers.add("Accept", mediaType);
        return headers;
    }

    /**
     * 设置请求头
     */
    public void setHeader(Map<String,String> headMap) {
        headers = createHeader(MediaType.APPLICATION_JSON_VALUE);
        Iterator<String> it = headMap.keySet().iterator();
        while (it.hasNext()){
            String key = it.next();
            headers.add(key, headMap.get(key));
        }
    }

    /**
     * 设置请求头
     */
    public HttpHeaders getHeader() {
        return headers == null? createHeader(MediaType.APPLICATION_JSON_VALUE):headers;
    }


    /**
     * 将 JSONObject 转为 a=1&b=2&c=3...&n=n 的形式
     */
    public String asUrlVariables(JSONObject variables) {
        Map<String, Object> source = variables.getInnerMap();
        Iterator<String> it = source.keySet().iterator();
        StringBuilder urlVariables = new StringBuilder();
        while (it.hasNext()) {
            String key = it.next();
            String value = "";
            Object object = source.get(key);
            if (object != null) {
                if (!StringUtils.isEmpty(object.toString())) {
                    value = object.toString();
                }
            }
            urlVariables.append("&").append(key).append("=").append(value);
        }
        // 去掉第一个&
        return urlVariables.substring(1);
    }
}
