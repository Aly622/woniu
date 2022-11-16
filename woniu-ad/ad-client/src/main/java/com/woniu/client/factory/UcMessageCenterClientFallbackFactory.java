package com.woniu.client.factory;

import com.woniu.client.UcMessageCenterClient;
import com.woniu.client.fallback.UcMessageCenterClientFallbackImpl;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UcMessageCenterClientFallbackFactory implements FallbackFactory<UcMessageCenterClient> {

    @Override
    public UcMessageCenterClient create(Throwable throwable) {
        UcMessageCenterClientFallbackImpl messageCenterClientFallback = new UcMessageCenterClientFallbackImpl();
        messageCenterClientFallback.setCause(throwable);
        return messageCenterClientFallback;
    }
}
