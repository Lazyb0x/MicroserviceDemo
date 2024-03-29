package cn.beanbang.orderservice.service;

import cn.beanbang.orderservice.dao.OrderDao;
import cn.beanbang.orderservice.entity.OrderDO;
import com.alibaba.fastjson.JSONObject;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @GlobalTransactional
    public Integer createOrder(Long userId, Long productId, Integer price) throws Exception {
        Integer amount = 1; // 购买数量，暂时设置为 1。

        logger.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // 扣减库存
        this.reduceStock(productId, amount);

        // 扣减余额
        this.reduceBalance(userId, price);

        // 保存订单
        OrderDO order = new OrderDO().setUserId(userId).setProductId(productId).setPayAmount(amount * price);
        orderDao.saveOrder(order);
        logger.info("[createOrder] 保存订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }

    private void reduceStock(Long productId, Integer amount) throws IOException {
//        // 参数拼接
        JSONObject params = new JSONObject().fluentPut("productId", String.valueOf(productId))
                .fluentPut("amount", String.valueOf(amount));
//        // 执行调用
//        HttpResponse response = DefaultHttpExecutor.getInstance().executePost("http://127.0.0.1:8082", "/product/reduce-stock",
//                params, HttpResponse.class);
//        // 解析结果
//        Boolean success = Boolean.valueOf(EntityUtils.toString(response.getEntity()));
//        if (!success) {
//            throw new RuntimeException("扣除库存失败");
//        }

        Boolean success = restTemplate.postForObject("http://product-service/product/reduce-stock", params, Boolean.class);
        if (Boolean.FALSE.equals(success)) {
            throw new RuntimeException("扣除库存失败");
        }
    }

    private void reduceBalance(Long userId, Integer price) throws IOException {
//        // 参数拼接
        JSONObject params = new JSONObject().fluentPut("userId", String.valueOf(userId))
                .fluentPut("price", String.valueOf(price));
//        // 执行调用
//        HttpResponse response = DefaultHttpExecutor.getInstance().executePost("http://127.0.0.1:8083", "/account/reduce-balance",
//                params, HttpResponse.class);
//        // 解析结果
//        Boolean success = Boolean.valueOf(EntityUtils.toString(response.getEntity()));
//        if (!success) {
//            throw new RuntimeException("扣除余额失败");
//        }

        Boolean success = restTemplate.postForObject("http://account-service/account/reduce-balance", params, Boolean.class);
        if (Boolean.FALSE.equals(success)) {
            throw new RuntimeException("扣除余额失败");
        }
    }

}
