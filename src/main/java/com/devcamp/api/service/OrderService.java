package com.devcamp.api.service;

import com.devcamp.api.repository.CustomerRepository;
import com.devcamp.api.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Date;

import com.devcamp.api.model.Customer;
import com.devcamp.api.model.Order;
import com.devcamp.api.model.OrderDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    //Lấy tất cả
    public List<Order> getAllOrders(){
        List<Order> allOrders = new ArrayList<>();
        orderRepository.findAll().forEach(allOrders::add);
        return allOrders;
    }

    //Lấy theo trạng thái đơn hàng
    public List<Order> getOrdersByStatus(String status){
        List<Order> allOrders = new ArrayList<>();
        orderRepository.findByStatus(status).forEach(allOrders::add);
        return allOrders;
    }

    //Lay theo phan trang
    public Page<Order> getAllOrderPageable(Integer page){
        Page<Order> orders = orderRepository.findAll(PageRequest.of(page, 8));
        return orders;
    }   

    //Lấy theo id
    public Order getById(Integer id){
        Optional<Order> order = orderRepository.findById(id);
        Order orderData = order.get();
        return orderData;
    }

    //Lấy danh sách đơn hàng theo id của khách hàng
    public List<Order> getOrdersByCustomerId(int customerId){
        List<Order> allOrders = new ArrayList<>();
        orderRepository.findByCustomerId(customerId).forEach(allOrders::add);
        return allOrders;
    }

    //Tạo đơn hàng mới 
    public Order createOrder(Order pOrder,Integer id){
        Optional<Customer> customer = customerRepository.findById(id);
        Customer customerData = customer.get();
        Order newOrder = new Order();
        newOrder.setOrderDate(new Date());
        newOrder.setRequiredDate(new Date());
        newOrder.setShippedDate(new Date());
        newOrder.setStatus("Chờ xác nhận");
        newOrder.setComments(pOrder.getComments());
        newOrder.setCustomer(customerData);

        Order saveOrder = orderRepository.save(newOrder);
        return saveOrder;
    }

    //Xác nhận đơn hàng
    public Order acceptOrder(Order pOrder, int id){
        Optional<Order> findOrder = orderRepository.findById(id);
        Order orderData = findOrder.get();
        orderData.setRequiredDate(new Date());
        ;
        orderData.setStatus("Đã xác nhận");
        orderData.setComments(pOrder.getComments());

        Order saveOrder = orderRepository.save(orderData);
        return saveOrder;
    }

    //Xác nhận đơn hàng đã giao 
    public Order shippedOrder(Order pOrder, int id){
        Optional<Order> findOrder = orderRepository.findById(id);
        Order orderData = findOrder.get();
        orderData.setShippedDate(new Date());
        orderData.setStatus("Đã giao xong");
        orderData.setComments(pOrder.getComments());

        Order saveOrder = orderRepository.save(orderData);
        return saveOrder;
    }

    //Cập nhật đơn hàng theo id
    public Order updateOrder(Order pOrder,int id){
        Optional<Order> findOrder = orderRepository.findById(id);
        Order orderData = findOrder.get();
        orderData.setOrderDate(pOrder.getOrderDate());
        orderData.setShippedDate(pOrder.getShippedDate());
        orderData.setRequiredDate(pOrder.getRequiredDate());
        orderData.setStatus(pOrder.getStatus());
        orderData.setComments(pOrder.getComments());

        Order saveOrder = orderRepository.save(orderData);
        return saveOrder;
    } 

    //Xoá tất cả
    public void deleteAll(){
        orderRepository.deleteAll();
    }

    //Xoá theo id
    public void deleteById(int id){
        orderRepository.deleteById(id);
    }

    //Lấy các đơn hàng nhỏ của đơn hàng này
    public List<OrderDetail> allOrderDetailsOfOrder(int id){
        Optional<Order> findOrder = orderRepository.findById(id);
        Order orderData = findOrder.get();
        List<OrderDetail> orderDetails = orderData.getOrderDetails();
        return orderDetails;
    }
    
    //Lấy thông tin kháhc hàng của đơn hàng này theo id đơn hàng
    public Customer getCustomerInfo(Integer id){
        Optional<Order> order = orderRepository.findById(id);
        Order orderData = order.get();
        Customer customer = orderData.getCustomer();
        return customer;
    }

    //Lấy theo khoảng thời gian
    public List<Order> getByRangeTime(String beginDate,String endDate){
        List<Order> orders = orderRepository.findByDate(beginDate, endDate);
        return orders;
    }

}
