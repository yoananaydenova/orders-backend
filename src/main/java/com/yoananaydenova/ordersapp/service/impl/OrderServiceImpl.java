package com.yoananaydenova.ordersapp.service.impl;

import com.yoananaydenova.ordersapp.model.Item;
import com.yoananaydenova.ordersapp.model.Order;
import com.yoananaydenova.ordersapp.model.OrderItem;
import com.yoananaydenova.ordersapp.model.dtos.AddOrderDTO;
import com.yoananaydenova.ordersapp.model.dtos.OrderDTO;
import com.yoananaydenova.ordersapp.model.dtos.OrderItemDTO;
import com.yoananaydenova.ordersapp.repository.OrderRepository;
import com.yoananaydenova.ordersapp.repository.OrderItemRepository;
import com.yoananaydenova.ordersapp.service.ItemService;
import com.yoananaydenova.ordersapp.service.OrderService;
import com.yoananaydenova.ordersapp.exception.ItemQuantityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemService itemService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemService = itemService;
    }

    @Override
    public OrderDTO createOrder(AddOrderDTO addOrderDTO) {

        final Order newOrder = orderRepository.save(new Order());

        final List<String> filedAddedItems = new ArrayList<>();

        final List<OrderItem> orderItems = addOrderDTO.items().stream().map(dto -> {
            try {
                return createOrderItem(newOrder, dto);
            } catch (ItemQuantityException ex) {
                filedAddedItems.add(ex.getMessage());
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        final List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);

        newOrder.setItems(new HashSet<>(savedOrderItems));

        orderRepository.save(newOrder);

        final List<OrderItemDTO> resultItems = convertOrderItemIntoDTO(savedOrderItems);

        return new OrderDTO(newOrder.getOrderId(), newOrder.getCreatedOn(), null, newOrder.getTotalAmount(), resultItems, filedAddedItems);
    }

    private static List<OrderItemDTO> convertOrderItemIntoDTO(List<OrderItem> savedOrderItems) {
        return savedOrderItems.stream()
                .map(i -> new OrderItemDTO(i.getId(), i.getItem().getName(), i.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order-> new OrderDTO(order.getOrderId(),
                        order.getCreatedOn(), order.getUpdatedOn(), order.getTotalAmount(), createOrderItemDTOs(order.getItems()), new ArrayList<>())).collect(Collectors.toList());
    }

    private List<OrderItemDTO> createOrderItemDTOs(Set<OrderItem> orderItems) {
        return orderItems.stream().map(i-> new OrderItemDTO(i.getId(),i.getItem().getName(), i.getQuantity())).collect(Collectors.toList());
    }


    private Order getOrder(long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException(
                        """
                                The order with id: "%s" does not exist!""".formatted(orderId)));
    }

    @Transactional
    private OrderItem createOrderItem(Order order, OrderItemDTO orderItemDTO) {
        final Item item = itemService.getItem(orderItemDTO);

        final int availableQuantity = item.getAvailableQuantity();
        final int orderItemQuantity = orderItemDTO.quantity();

        validateQuantity(orderItemDTO.itemName(), availableQuantity, orderItemQuantity);

        item.setAvailableQuantity(calculateAvailableQuantity(availableQuantity, orderItemQuantity));
        return new OrderItem(item.getCurrentPrice(), orderItemQuantity, item, order);
    }

    private static int calculateAvailableQuantity(int availableQuantity, int orderItemQuantity) {
        return availableQuantity - orderItemQuantity;
    }

    private static void validateQuantity(String itemName, int availableQuantity, int orderItemQuantity) {
        if (availableQuantity >= orderItemQuantity) {
            return;
        }
        throw new ItemQuantityException("""
                The item %s has available quantity %s and is less than ordered item quantity %s!""".formatted(itemName, availableQuantity, orderItemQuantity));

    }


}
