package ee.ege.veebipood.controller;

import ee.ege.veebipood.entity.Order;
import ee.ege.veebipood.entity.Person;
import ee.ege.veebipood.model.payment.PaymentLink;
import ee.ege.veebipood.model.payment.PaymentStatus;
import ee.ege.veebipood.repository.OrderRepository;
import ee.ege.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("order/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @PostMapping("order")
    public PaymentLink saveOrder(@RequestBody Order order) {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Person person = new Person();
        person.setEmail(email);
        order.setPerson(person);
        order.setPaid(false);
        Order savedOrder = orderService.saveOrder(order);
        return orderService.getPaymentLink(savedOrder);
    }

    @GetMapping("check-payment/{paymentReference}")
    public PaymentStatus getOrderById(@PathVariable String paymentReference) {
        return orderService.checkPaymentStatus(paymentReference);
    }
}
