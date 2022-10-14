package com.devcamp.api.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.devcamp.api.model.Customer;
import com.devcamp.api.model.Order;
import com.devcamp.api.model.OrderDetail;
import com.devcamp.api.service.OrderService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/")
public class OrderController {
    
    @Autowired
    OrderService orderService;

    //Lất tất cả đơn hàng
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        try {
            List<Order> allOrders = orderService.getAllOrders();
            return new ResponseEntity<>(allOrders,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lay theo phan trang 
    @GetMapping("/orders/pageable/{page}")
    public ResponseEntity<Page<Order>> getAllOrdersPageable(@PathVariable("page") Integer page){
        try {
            Page<Order> orders = orderService.getAllOrderPageable(page);
            return new ResponseEntity<>(orders,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Lấy theo id
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getById(@PathVariable("id") int id){
        try {
            Order order = orderService.getById(id);
            return new ResponseEntity<>(order,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Lấy đơn hàng theo trạng thái
    @GetMapping("/orders/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable("status") String status){
        try {
            List<Order> orders = orderService.getOrdersByStatus(status);
            return new ResponseEntity<>(orders,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy đơn hàng theo id của khách hàng
    @GetMapping("/orders/customerId/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable("customerId") int customerId){
        try {
            List<Order> orders = orderService.getOrdersByCustomerId(customerId);
            return new ResponseEntity<>(orders,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Tạo mới đơn hàng 
    @PostMapping("/orders/customerId/{customerId}")
    public ResponseEntity<Object> createOrder(@Valid @RequestBody Order pOrder,@PathVariable("customerId") Integer customerId){
        try {
            Order newOrder = orderService.createOrder(pOrder,customerId);
            return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xác nhận đơn hàng
    @PutMapping("/orders/accept/{id}")
    public ResponseEntity<Object> acceptOrder(@Valid @RequestBody Order pOrder,@PathVariable("id") int id){
        try {
            Order newOrder = orderService.acceptOrder(pOrder,id);
            return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xác nhận đơn hàng đã giao xong
    @PutMapping("/orders/shipped/{id}")
    public ResponseEntity<Object> shippedOrder(@Valid @RequestBody Order pOrder,@PathVariable("id") int id){
        try {
            Order newOrder = orderService.acceptOrder(pOrder,id);
            return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá tất cả đơn hàng
    @DeleteMapping("/orders")
    public ResponseEntity<Order> deleteAll(){
        try {
            orderService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá đơn hàng theo id
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Order> deleteById(@PathVariable("id") int id){
        try {
            orderService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Lấy các đơn hàng nhỏ của đơn hàng theo id
    @GetMapping("orderdetails/orderId/{id}")
    public ResponseEntity<List<OrderDetail>> allOrderDetailsOfOrder(@PathVariable("id") int id){
        try {
            List<OrderDetail> orderDetails = orderService.allOrderDetailsOfOrder(id);
            return new ResponseEntity<>(orderDetails,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Cập nhật đơn hàng
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") int id,@Valid @RequestBody Order pOrder){
        try {
            Order order = orderService.updateOrder(pOrder, id);
            return new ResponseEntity<>(order,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy thông tin khách hàng theo id đơn hàng
    @GetMapping("/customers/orderId/{orderId}")
    public ResponseEntity<Customer> getCustomerByOrderId(@PathVariable("orderId") Integer orderId){
        try {
            Customer customer = orderService.getCustomerInfo(orderId);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/rangeTime/{beginDate}/{endDate}")
    public ResponseEntity<List<Order>> getByRangeTime(@PathVariable("beginDate") String beginDate,@PathVariable("endDate") String endDate){
        try {
            List<Order> orders = orderService.getByRangeTime(beginDate,endDate);
            return new ResponseEntity<>(orders,HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}   
