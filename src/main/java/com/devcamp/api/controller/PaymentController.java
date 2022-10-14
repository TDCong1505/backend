package com.devcamp.api.controller;

import java.util.List;
import com.devcamp.api.model.Payment;
import com.devcamp.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PaymentController {
    
    @Autowired
    PaymentService paymentService;

    //GET ALL
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments(){
        try {
            List<Payment> payments = paymentService.getAllPayments();
            return new ResponseEntity<>(payments,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET BY CUSTOMER ID
    @GetMapping("/payments/customer-id/{customerId}")
    public ResponseEntity<List<Payment>> getPaymentsByCustomerId(@PathVariable("customerId") int customerId){
        try {
            List<Payment> payments = paymentService.getPaymentsByCustomerId(customerId);
            return new ResponseEntity<>(payments,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET BY ID 
    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") int id){
        try {
            Payment payment =  paymentService.getPaymentById(id);
            return new ResponseEntity<>(payment,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST
    @PostMapping("/customers/{id}/payments")
    public ResponseEntity<Object> createPayment(@PathVariable("id") int id,@RequestBody Payment pPayment){
        try {
            Payment payment = paymentService.createPayment(pPayment, id);
            return new ResponseEntity<>(payment,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //UPDATE 
    @PutMapping("/payments/{id}")
    public ResponseEntity<Object> updatePayment(@PathVariable("id") int id,@RequestBody Payment pPayment){
        try {
            Payment payment = paymentService.updatePayment(pPayment, id);
            return new ResponseEntity<>(payment,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE ALL
    @DeleteMapping("/payments")
    public ResponseEntity<Payment> deleteAllPayments(){
        try {
            paymentService.deleteAllPayments();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE BY ID
    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Payment> deletePaymentById(@PathVariable("id")  int id){
        try {
            paymentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
