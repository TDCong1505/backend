package com.devcamp.api.service;

import java.util.List;
import java.util.Optional;

import com.devcamp.api.model.Customer;
import com.devcamp.api.model.Payment;
import com.devcamp.api.repository.CustomerRepository;
import com.devcamp.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class PaymentService {
    
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    CustomerRepository customerRepository;
    //GET ALL
    public List<Payment> getAllPayments(){
        List<Payment> allPayments = new ArrayList<>();
        paymentRepository.findAll().forEach(allPayments::add);
        return allPayments;
    }

    //GET BY ID
    public Payment getPaymentById(int id){
        Optional<Payment> findPayment = paymentRepository.findById(id);
        Payment payment = findPayment.get();
        return payment;
    }

    //GET BY CUSTOMER ID
    public List<Payment> getPaymentsByCustomerId(int customerId){
        List<Payment> allPayments = new ArrayList<>();
        paymentRepository.findByCustomerId(customerId).forEach(allPayments::add);
        return allPayments;
    }

    //POST 
    public Payment createPayment(Payment pPayment,int id){
        Optional<Customer> findCustomer = customerRepository.findById(id);
        Customer customerData = findCustomer.get();
        Payment newPayment = new Payment();
        newPayment.setAmmount(pPayment.getAmmount());
        newPayment.setCheckNumber(pPayment.getCheckNumber());
        newPayment.setPaymentDate(pPayment.getPaymentDate());
        newPayment.setCustomer(customerData);
        Payment savePayment = paymentRepository.save(newPayment);
        return savePayment;
    }

    //UPDATE 
    public Payment updatePayment(Payment pPayment,int id){
        Optional<Payment> findPayment = paymentRepository.findById(id);
        Payment paymentData = findPayment.get();
        paymentData.setAmmount(pPayment.getAmmount());
        paymentData.setCheckNumber(pPayment.getCheckNumber());
        paymentData.setPaymentDate(pPayment.getPaymentDate());
        Payment savePayment = paymentRepository.save(paymentData);
        return savePayment;
    }

    //DELETE ALL
    public void deleteAllPayments(){
        paymentRepository.deleteAll();
    }

    //DELETE BY ID
    public void deleteById(int id){
        paymentRepository.deleteById(id);
    }
}
