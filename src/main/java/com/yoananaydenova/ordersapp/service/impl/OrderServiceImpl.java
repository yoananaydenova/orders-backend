package com.yoananaydenova.ordersapp.service.impl;

import com.yoananaydenova.ordersapp.exception.ItemNotFoundException;
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
import com.yoananaydenova.ordersapp.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
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

        final List<OrderItemDTO> resultItems = convertOrderItemIntoDTOs(savedOrderItems);

        return new OrderDTO(newOrder.getOrderId(), newOrder.getCreatedOn(), null, newOrder.getTotalAmount(), resultItems, filedAddedItems);
    }

    private static List<OrderItemDTO> convertOrderItemIntoDTOs(List<OrderItem> savedOrderItems) {
        return savedOrderItems.stream()
                .map(i -> new OrderItemDTO(i.getItemId(), i.getItemName(), i.getPrice(), i.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::createOrderDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(Long id) {
        return createOrderDTO(findOrderById(id));
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    // TODO here
    @Override
    public OrderDTO updateOrderById(Long id, AddOrderDTO addOrderDTO) {
        final Order order = findOrderById(id);
        order.setUpdatedOn(LocalDateTime.now());


        List<Long> oldOrderItemIds =  order.getItems().stream().map(OrderItem::getItemId).toList();
        List<Long> newOrderItemIds = addOrderDTO.items().stream().map(OrderItemDTO::id).toList();

//        List<String> list = new ArrayList<>(CollectionUtils.disjunction(oldOrderItemIds, newOrderItemIds));


        addOrderDTO.items().forEach(item->{
            if(oldOrderItemIds.contains(item.id())){
                updateItemList(item);
            }else{

            }
        });
        return null;
    }

    private void updateItemList(OrderItemDTO orderItem) {

        final Item item;
        try {
            item =  itemService.findById(orderItem.id());
        }catch (ItemNotFoundException ex){

        }
//        if(orderItem.quantity() )

    }

    @Override
    public String deleteOrderById(Long id) {

       final Order order = findOrderById(id);

       final List<String> itemDeletionResult = order.getItems().stream().map(this::deleteOrderItem).toList();

        orderRepository.deleteById(id);

        return """
               Order with id %s has been successfully deleted!""".formatted(id);
    }

    private String deleteOrderItem(OrderItem orderItem){

      final  Long itemId = orderItem.getItemId();
        final Item item;
        try {
             item = itemService.findById(itemId);
        }catch (ItemNotFoundException ex){
           return """
               Item with id %s was deleted from the database!""".formatted(itemId);
        }

        item.setAvailableQuantity(item.getAvailableQuantity() + orderItem.getQuantity());

        return """
               Item with id %s was successfully deleted from the order!""".formatted(itemId);
    }

    private List<OrderItemDTO> createOrderItemDTOs(Set<OrderItem> orderItems) {
        return orderItems.stream().map(i-> new OrderItemDTO(i.getItemId(),i.getItemName(),i.getPrice(), i.getQuantity())).collect(Collectors.toList());
    }

    private OrderDTO createOrderDTO(Order order){
        return new OrderDTO(order.getOrderId(),
                order.getCreatedOn(), order.getUpdatedOn(), order.getTotalAmount(), createOrderItemDTOs(order.getItems()));
    }

    @Transactional
    private OrderItem createOrderItem(Order order, OrderItemDTO orderItemDTO) {
        final Item item = itemService.findById(orderItemDTO.id());

        final int availableQuantity = item.getAvailableQuantity();
        final int orderItemQuantity = orderItemDTO.quantity();

        validateQuantity(orderItemDTO.name(), availableQuantity, orderItemQuantity);

        item.setAvailableQuantity(availableQuantity - orderItemQuantity);

        return new OrderItem(item.getItemId(), item.getCurrentPrice(), orderItemQuantity,item.getName(),  order);
    }

    private static void validateQuantity(String itemName, int availableQuantity, int orderItemQuantity) {
        if (availableQuantity >= orderItemQuantity) {
            return;
        }
        throw new ItemQuantityException(itemName, availableQuantity, orderItemQuantity);

    }


}
