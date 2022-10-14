package com.devcamp.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.api.entity.User;
import com.devcamp.api.repository.UserRepository;
import com.devcamp.api.service.UserService;

/**
 * @author hieuha
 *
 */
@RestController
@CrossOrigin
public class WithoutAuthorizeController {

	@Autowired
	private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Object>> getUsers() {
        return new ResponseEntity(userRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("users/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        try {
            User user = userRepository.findByUsername(username);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Lấy user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        try {
            Optional<User> user = userRepository.findById(id);
            User userData = user.get();
            return new ResponseEntity<>(userData,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy phân trang
    @GetMapping("/users/pageable/{page}")
    public ResponseEntity<Page<User>> getUsersPageable(@PathVariable("page") Integer page){
        try {
            Page<User> users = userRepository.findAll(PageRequest.of(page, 8));
            return new ResponseEntity<>(users,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
