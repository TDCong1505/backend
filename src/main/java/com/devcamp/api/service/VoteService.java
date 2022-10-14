package com.devcamp.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.devcamp.api.model.Customer;
import com.devcamp.api.model.Product;
import com.devcamp.api.model.Vote;
import com.devcamp.api.repository.CustomerRepository;
import com.devcamp.api.repository.ProductRepository;
import com.devcamp.api.repository.VoteRepository;
import java.util.ArrayList;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    //Lấy tất cả bình luận
    public List<Vote> getAll(){
        List<Vote> votes = new ArrayList<>();
        voteRepository.findAll().forEach(votes::add);
        return votes;
    }

    //Lấy tất cả bình luận của một khách hàng
    public List<Vote> getVotesOfCustomer(int customerId){
        List<Vote> votes = voteRepository.findByCustomerId(customerId);
        return votes;
    }

    //Lấy danh sách bình luận theo mã sản phẩm
    public List<Vote> getVotesOfProduct(String productCode){
        Product product = productRepository.findByProductCode(productCode);
        List<Vote> votes = product.getVotes();
        return votes;
    }

    //Lấy theo phân trang 
    public Page<Vote> getVotesPageable(Integer page){
        Page<Vote> votes = voteRepository.findAll(PageRequest.of(page, 8));
        return votes;
    }

    //Tạo mới một bình chọn
    public Vote createVote(int productId,int customerId,Vote pVote){
        Vote vote = new Vote();
        Optional<Customer> findCustomer = customerRepository.findById(customerId);
        Customer customerData = findCustomer.get();
        Optional<Product> findProduct = productRepository.findById(productId);
        Product productData =  findProduct.get();

        vote.setVote(pVote.getVote());
        vote.setComment(pVote.getComment());
        vote.setCustomer(customerData);
        vote.setProduct(productData);
        vote.setTimeVote(new Date());

        Vote saveVote = voteRepository.save(vote);
        return saveVote;

    }

    //Sửa một bình chọn
    public Vote updateVote(int id,Vote pVote){
        Optional<Vote> findVote = voteRepository.findById(id);
        Vote voteData = findVote.get();
        voteData.setVote(pVote.getVote());
        voteData.setComment(pVote.getComment());

        Vote saveVote = voteRepository.save(voteData);
        return voteData;
    }

    //Xoá tất cả 
    public void deleteAll(){
        voteRepository.deleteAll();
    }

    //Xoá một bình chọn
    public void deleteById(int id){
        voteRepository.deleteById(id);
    }

}
