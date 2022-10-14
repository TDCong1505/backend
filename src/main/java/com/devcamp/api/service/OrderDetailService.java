package com.devcamp.api.service;

import com.devcamp.api.model.Order;
import com.devcamp.api.model.OrderDetail;
import com.devcamp.api.model.Product;
import com.devcamp.api.repository.OrderDetailRepository;
import com.devcamp.api.repository.OrderRepository;
import com.devcamp.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    //Lấy tất cả danh sách đơn hàng
    public List<OrderDetail> getAll(){
        List<OrderDetail> allOrderDetails = new ArrayList<>();
        orderDetailRepository.findAll().forEach(allOrderDetails::add);
        return allOrderDetails;
    }

    //Lấy đơn hàng theo id 
    public OrderDetail getById(int id){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        OrderDetail orderDetailData = orderDetail.get();
        return orderDetailData;
    }

    //Lấy theo phân trang 
    public Page<OrderDetail> getAllOrderDetailPageable(Integer page){
        Page<OrderDetail> orderDetails = orderDetailRepository.findAll(PageRequest.of(page, 8));
        return orderDetails;
    }
    
    //Tạo mới một đơn hàng
    public OrderDetail createOrderDetail(String productCode,int orderId,OrderDetail pOrderDetail){
        Optional<Order> findOrder = orderRepository.findById(orderId);
        Order orderData = findOrder.get();
        Product product = productRepository.findByProductCode(productCode);
        OrderDetail newOrderDetail = new OrderDetail();
        newOrderDetail.setQuantityOrder(pOrderDetail.getQuantityOrder());
        newOrderDetail.setPriceEach(pOrderDetail.getPriceEach());
        newOrderDetail.setProduct(product);
        newOrderDetail.setOrder(orderData);

        OrderDetail saveOrderDetail = orderDetailRepository.save(newOrderDetail);
        return saveOrderDetail;
    }

    //Cập nhật một đơn hàng theo id 
    public OrderDetail updateOrderDetail(OrderDetail pOrderDetail,int id){
        Optional<OrderDetail> findOrderDetail = orderDetailRepository.findById(id);
        OrderDetail orderDetailData = findOrderDetail.get();
        orderDetailData.setQuantityOrder(pOrderDetail.getQuantityOrder());
        orderDetailData.setPriceEach(pOrderDetail.getPriceEach());
        OrderDetail saveOrderDetail = orderDetailRepository.save(orderDetailData);
        return saveOrderDetail;
    }

    //Xoá tất cả    
    public void deleteAll(){
        orderDetailRepository.deleteAll();
    }
    
    //Xoá theo id
    public void deleteById(int id){
        orderDetailRepository.deleteById(id);
    }

}
