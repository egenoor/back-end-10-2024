package ee.ege.veebipood.service;

import ee.ege.veebipood.cache.ProductCache;
import ee.ege.veebipood.entity.Order;
import ee.ege.veebipood.entity.OrderRow;
import ee.ege.veebipood.entity.Product;
import ee.ege.veebipood.model.payment.*;
import ee.ege.veebipood.repository.OrderRepository;
import ee.ege.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductCache productCache;

    // saab application propertitest väärtuse kätte
    @Value(("${everypay-url}"))
    String everyPayUrl;

    @Value(("${everypay-username}"))
    String everyPayUsername;

    @Value(("${everypay-authorization}"))
    String everyPayAuthorization;

    public Order saveOrder(Order order) throws ExecutionException {
        //List<OrderRow> orderRows = orderRowRepository.saveAll(order.getOrderRows());
        // order.setOtderRows(orderRows);
        // peaaegu võrdne cascade type alliga (kustutada ei oska nagu cascade)
        order.setCreation(new Date());
        double totalSum = 0;
        for(OrderRow row: order.getOrderRows()){
            // Product dbProduct = productRepository.findById(row.getProduct().getId()).orElseThrow();
            Product dbProduct = productCache.getProduct(row.getProduct().getId());
            totalSum += dbProduct.getPrice() * row.getPcs();
        }
        order.setTotalSum(totalSum);
        return orderRepository.save(order);
    }

    public PaymentLink getPaymentLink(Order order) {
        String url = everyPayUrl + "/api/v4/payments/oneoff";
        EveryPayBody body = new EveryPayBody();
        body.setAccount_name("EUR3D1");
        body.setNonce("fdfd" + ZonedDateTime.now() + Math.random());
        body.setTimestamp(ZonedDateTime.now().toString());
        body.setAmount(order.getTotalSum());
        body.setOrder_reference(order.getId().toString());
        body.setCustomer_url("https://err.ee");
        body.setApi_username(everyPayUsername);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, everyPayAuthorization);
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<EveryPayBody> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<EveryPayResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                EveryPayResponse.class
        );
        PaymentLink link = new PaymentLink();
        link.setLink(response.getBody().getPayment_link());
        return link;
    }

    public PaymentStatus checkPaymentStatus(String paymentReference) {
        String url = everyPayUrl + "/api/v4/payments/" +
                paymentReference + "?api_username=" +
                everyPayUsername + "&detailed=false";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, everyPayAuthorization);
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<PaymentStatusResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                PaymentStatusResponse.class
        );
        PaymentStatus status = new PaymentStatus();
        status.setStatus(response.getBody().getPayment_state());
        return status;
    }
}
