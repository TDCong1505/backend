package com.devcamp.api.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.devcamp.api.model.Customer;
import com.devcamp.api.repository.CustomerRepository;
import com.devcamp.api.service.CustomerService;
import com.devcamp.api.service.ExcelExporter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
@RequestMapping("/")
@CrossOrigin
public class CustomerController {
    
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/export/customers/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<Customer> customer = new ArrayList<Customer>();

		customerRepository.findAll().forEach(customer::add);

		ExcelExporter excelExporter = new ExcelExporter(customer);

		excelExporter.export(response);
	}

    //L???y t???t c???
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        try {
            List<Customer> allCustomers = customerService.getAllCustomers();
            return new ResponseEntity<>(allCustomers,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //L???y theo ph??n trang
    @GetMapping("/customers/pageable/{page}")
    public ResponseEntity<Page<Customer>> getAlLCustomersPageable(@PathVariable("page") Integer page){
        try {
            Page<Customer> customers = customerService.allCustomer(page);
            return new ResponseEntity<>(customers,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //L???y th??ng tin kh??ch h??ng theo s??? ??i???n tho???i
    @GetMapping("/customers/phoneNumber/{phoneNumber}")
    public ResponseEntity<Customer> getCustomersByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        try {
            Customer customer = customerService.getCustomerByPhoneNumber(phoneNumber);
            return new ResponseEntity<>(customer,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //L???y danh s??ch kh??ch h??ng theo qu???c gia
    @GetMapping("/customers/country/{country}")
    public ResponseEntity<List<Customer>> getCustomersByCountry(@PathVariable("country") String country){
        try{
            List<Customer> customers = customerService.getByCountry(country);
            return new ResponseEntity<>(customers,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //L???y danh s??ch kh??ch h??ng theo th??nh ph???
    @GetMapping("/customers/city/{city}")
    public ResponseEntity<List<Customer>> getCustomersByCity(@PathVariable("city") String city){
        try{
            List<Customer> customers = customerService.getByCity(city);
            return new ResponseEntity<>(customers,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //L???y th??ng tin kh??ch h??ng theo id
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id){
        try {
            Customer customer = customerService.getById(id);
            return new ResponseEntity<Customer>(customer,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //L???y th??ng tin kh??ch h??ng theo email
    @GetMapping("/customers/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email") String email){
        try {
            Customer customer = customerService.getCustomerByEmail(email);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xo?? t???t c??? 
    @DeleteMapping("/customers")
    public ResponseEntity<Customer> deleteAllCustomers(){
        try {
            customerService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xo?? theo id
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteById(@PathVariable("id") int id){
        try {
            customerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //T???o m???i th??ng tin kh??ch h??ng 
    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody Customer pCustomer){
        try {
            Customer customer = customerService.createCustomer(pCustomer);
            return new ResponseEntity<>(customer,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //C???p nh???t th??ng tin kh??ch h??ng 
    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@Valid @RequestBody Customer pCustomer,@PathVariable("id") int id){
        try {
            Customer customer = customerService.updateCustomer(pCustomer,id);
            return new ResponseEntity<>(customer,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Search
    @GetMapping("/customers/search")
    public ResponseEntity<List<Customer>> searchProducts(@RequestParam("query") String query){
        try {
            List<Customer> customers = customerService.searchCustomer(query);
            return new ResponseEntity<>(customers,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
