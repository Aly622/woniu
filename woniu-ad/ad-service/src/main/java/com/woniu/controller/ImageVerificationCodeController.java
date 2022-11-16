package com.woniu.controller;


import com.woniu.constants.UcConstants;
import com.woniu.dto.VerifiCodeDTO;
import com.woniu.response.UCResponseCode;
import com.esmartwave.niumeng.diap.response.WebResponse;
import com.esmartwave.niumeng.diap.utils.Base64;
import com.esmartwave.niumeng.diap.utils.ImageVerificationCodeUtils;
import com.woniu.utils.RedisUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.UUID;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/captchaImage")
public class ImageVerificationCodeController extends SuperController {

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "获取图片验证码")
    @GetMapping("/get")
    public WebResponse<VerifiCodeDTO> getVerifiCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ImageVerificationCodeUtils ivc = new ImageVerificationCodeUtils();//用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage();//获取验证码
        log.info("#### 获取图片验证码：ivc.getText() = {}", ivc.getText());
        String uuid = UUID.randomUUID().toString();
        String verifyKey = UcConstants.CAPTCHA_CODE_KEY + uuid;
        redisUtils.set(verifyKey, ivc.getText());
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "JPEG", os);
        return new WebResponse(UCResponseCode.SUCCESS, new VerifiCodeDTO(uuid, Base64.encode(os.toByteArray())));
    }

}

