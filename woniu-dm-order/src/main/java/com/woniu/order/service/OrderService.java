package com.woniu.order.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.woniu.order.feign.AccountFeignClient;
import com.woniu.order.mapper.OrderMapper;
import com.woniu.order.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * 描述：
 *
 * @author 含光
 * @email jarvan_best@163.com
 * @date 2021/2/26 10:56 上午
 * @company 数海掌讯
 */
@Slf4j
@Service
public class OrderService {

    @Resource
    private AccountFeignClient accountFeignClient;
    @Resource
    private OrderMapper orderMapper;
    //@Transactional(rollbackFor = Exception.class)
    public void create(String userId, String commodityCode, Integer count) {
        //订单金额
        Integer orderMoney = count * 2;
        Random r = new Random();
        Order order = new Order().setUserId(userId)
                .setCommodityCode(commodityCode)
                .setCount(count)
                .setMoney(orderMoney)
                .setId(r.nextInt(10000));
        orderMapper.insert(order);

        accountFeignClient.reduce(userId, orderMoney);
    }
}
