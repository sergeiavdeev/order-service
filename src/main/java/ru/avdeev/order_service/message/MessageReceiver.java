package ru.avdeev.order_service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.AcknowledgableDelivery;
import ru.avdeev.order_service.dto.BookingDto;
import ru.avdeev.order_service.dto.OrderDto;
import ru.avdeev.order_service.dto.OrderProductDto;
import ru.avdeev.order_service.service.OrderService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageReceiver {

    private final Flux<AcknowledgableDelivery> listener;
    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @PostConstruct
    public void listen() {

        listener.map(delivery -> {
            String msgType = delivery.getProperties().getHeaders().get("type").toString();
            String body = new String(delivery.getBody(), StandardCharsets.UTF_8);
            Message msg = new Message(msgType, body, delivery);
            log.info("Received message {}", msg);
            return msg;
        })
        .subscribe(msg -> {
            if (msg.getType().equals("BookingCreated")) {
                bookingCreatedHandler(msg);
            } else if (msg.getType().equals("CancelOrder")) {
                cancelOrderHandler(msg);
            } else {
                msg.getDelivery().ack();
            }

        });
    }

    private void cancelOrderHandler(Message message) {
        OrderDto orderDto = toObject(message.getBody(), OrderDto.class);
        if (orderDto == null) {
            message.getDelivery().nack(true);
            return;
        }
        log.info("Cancel order handle start: {}", orderDto);
        orderService.cancelOrder(orderDto.getId()).subscribe(order -> {
            log.info("Order canceled: {}", order);
            message.getDelivery().ack();
        });
    }

    private void bookingCreatedHandler(Message message) {

        BookingDto bookingDto = toObject(message.getBody(), BookingDto.class);
        if (bookingDto == null) {
            message.getDelivery().nack(true);
            return;
        }
        log.info("Booking created handle start: {}", bookingDto);
        OrderDto order = new OrderDto();
        order.setUserId(bookingDto.getUserId());
        List<OrderProductDto> products = new ArrayList<>();
        OrderProductDto product = new OrderProductDto();
        product.setId(bookingDto.getId());
        product.setProductId(bookingDto.getResourceId());
        product.setCount(bookingDto.getCount());
        products.add(product);
        order.setProducts(products);
        orderService.saveOrder(order).subscribe(savedOrder -> {
            log.info("Order created: {}", savedOrder);
            message.getDelivery().ack();
        });
    }

    private <T> T toObject(String msg, Class<T> t) {
        try {
            return  objectMapper.readValue(msg, t);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Data
    @AllArgsConstructor
    private static class Message {
        private String type;
        private String body;
        private AcknowledgableDelivery delivery;
    }
}