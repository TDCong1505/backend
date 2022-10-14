package com.devcamp.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.devcamp.api.model.OrderDetail;
import com.devcamp.api.service.OrderDetailService;
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
@RequestMapping("/")
@CrossOrigin
public class OrderDetailController {
    
    @Autowired
    OrderDetailService orderDetailService;

    //Lấy tất cả
    @GetMapping("/orderdetails")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails(){
        try {
            List<OrderDetail> orderDetails = orderDetailService.getAll();
            return new ResponseEntity<>(orderDetails,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy theo id
    @GetMapping("orderdetails/{id}")
    public ResponseEntity<OrderDetail> getById(@PathVariable("id") int id){
        try {
            OrderDetail orderDetail = orderDetailService.getById(id);
            return new ResponseEntity<>(orderDetail,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }


    //Lấy theo phân trang
    @GetMapping("/orderdetails/pageable/{page}")
    public ResponseEntity<Page<OrderDetail>> getAllPageable(@PathVariable("page") Integer page){
        try {
            Page<OrderDetail> orderDetails = orderDetailService.getAllOrderDetailPageable(page);
            return new ResponseEntity<>(orderDetails,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Tạo mới một đơn hàng
    @PostMapping("/orderdetails/productCode/{productCode}/orderId/{orderId}")
    public ResponseEntity<OrderDetail> createOrderDetail(@PathVariable("productCode") String productCode,@PathVariable("orderId") int orderId,@Valid @RequestBody OrderDetail pOrderDetail){
        try {
            OrderDetail orderDetail = orderDetailService.createOrderDetail(productCode, orderId,pOrderDetail);
            return new ResponseEntity<>(orderDetail,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Cập nhật một đơn hàng
    @PutMapping("/orderdetails/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable("id") int id,@Valid @RequestBody OrderDetail pOrderDetail){
        try {
            OrderDetail orderDetail = orderDetailService.updateOrderDetail(pOrderDetail, id);
            return new ResponseEntity<>(orderDetail,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    //Xoá tất cả
    @DeleteMapping("/orderdetails")
    public ResponseEntity<OrderDetail> deleteAllOrderDetails(){
        try {
            orderDetailService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá theo id
    @DeleteMapping("/orderdetails/{id}")
    public ResponseEntity<OrderDetail> deleteOrderDetailById(@PathVariable("id") int id){
        try {
            orderDetailService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
