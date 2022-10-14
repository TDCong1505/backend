package com.devcamp.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.devcamp.api.model.Vote;
import com.devcamp.api.service.VoteService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class VoteController {
    
    @Autowired
    VoteService voteService;

    //Lấy tất cả bình luận 
    @GetMapping("/votes")
    public ResponseEntity<List<Vote>> allVotes(){
        try {
            List<Vote> allVotes = voteService.getAll();
            return new ResponseEntity<>(allVotes,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy tất cả bình luận của một khách hàng
    @GetMapping("votes/customerId/{customerId}")
    public ResponseEntity<List<Vote>> allVotesOfCustomer(@PathVariable("customerId") int customerId){
        try {
            List<Vote> votes = voteService.getVotesOfCustomer(customerId);
            return  new ResponseEntity<>(votes,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy theo phân trang
    @GetMapping("/votes/pageable/{page}")
    public ResponseEntity<Page<Vote>> allVotePageable(@PathVariable("page") Integer page){
        try {
            Page<Vote> votes = voteService.getVotesPageable(page);
            return new ResponseEntity<>(votes,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    //Lấy các bình luận theo mã sản phẩm
    @GetMapping("/votes/productCode/{productCode}")
    public ResponseEntity<List<Vote>> allVotesOfProduct(@PathVariable("productCode") String productCode){
        try {
            List<Vote> votes = voteService.getVotesOfProduct(productCode);
            return new ResponseEntity<>(votes,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Tạo mới một bình chọn
    @PostMapping("votes/customerId/{customerId}/productId/{productId}")
    public ResponseEntity<Vote> createVote(@PathVariable("customerId") int customerId,@PathVariable("productId") int productId,@Valid @RequestBody Vote pVote){
        try {
            Vote newVote = voteService.createVote(productId, customerId, pVote);
            return new ResponseEntity<>(newVote,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Sửa một bình chọn
    @PutMapping("/votes/{id}")
    public ResponseEntity<Vote> updateVote(@PathVariable("id") int id,@Valid @RequestBody Vote pVote){
        try {
            Vote vote = voteService.updateVote(id, pVote);
            return new ResponseEntity<>(vote,HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá tất cả bình luận
    @DeleteMapping("/votes")
    public ResponseEntity<Vote> deleteAllCustomers(){
        try {
            voteService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá theo id
    @DeleteMapping("/votes/{id}")
    public ResponseEntity<Vote> deleteById(@PathVariable("id") int id){
        try {
            voteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
