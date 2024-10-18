package ee.ege.veebipood.service;

import ee.ege.veebipood.entity.Order;
import ee.ege.veebipood.entity.OrderRow;
import ee.ege.veebipood.entity.Product;
import ee.ege.veebipood.repository.OrderRepository;
import ee.ege.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public void saveOrder(Order order) {
        //List<OrderRow> orderRows = orderRowRepository.saveAll(order.getOrderRows());
        // order.setOtderRows(orderRows);
        // peaaegu võrdne cascade type alliga (kustutada ei oska nagu cascade)
        order.setCreation(new Date());
        double totalSum = 0;
        for(OrderRow row: order.getOrderRows()){
            Product dbProduct = productRepository.findById(row.getProduct().getId()).orElseThrow();
            totalSum += dbProduct.getPrice() * row.getPcs();
        }
        order.setTotalSum(totalSum);
        orderRepository.save(order);
    }
}
