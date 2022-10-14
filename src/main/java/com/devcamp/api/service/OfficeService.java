package com.devcamp.api.service;

import java.util.List;
import java.util.Optional;

import com.devcamp.api.model.Office;
import com.devcamp.api.repository.OfficeRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {
    
    @Autowired
    OfficeRepository officeRepository;

    //GET ALL
    public List<Office> getAllOffices(){
        List<Office> allOffices = new ArrayList<>();
        officeRepository.findAll().forEach(allOffices::add);
        return allOffices;
    }

    //GET BY ID
    public Office getOfficeById(int id){
        Optional<Office> findOffice = officeRepository.findById(id);
        Office officeData = findOffice.get();
        return officeData;
    }
    
    //POST
    public Office createOffice(Office pOffice){
        Office newOffice = new Office();
        newOffice.setCity(pOffice.getCity());
        newOffice.setPhone(pOffice.getPhone());
        newOffice.setAddressLine(pOffice.getAddressLine());
        newOffice.setState(pOffice.getState());
        newOffice.setCountry(pOffice.getCountry());
        newOffice.setTerritory(pOffice.getTerritory());

        Office saveOffice  = officeRepository.save(newOffice);
        return saveOffice;
    }

    //PUT 
    public Office updateOffice (Office pOffice,int id){
        Optional<Office> findOffice = officeRepository.findById(id);
        Office officeData = findOffice.get();
        officeData.setCity(pOffice.getCity());
        officeData.setPhone(pOffice.getPhone());
        officeData.setAddressLine(pOffice.getAddressLine());
        officeData.setState(pOffice.getState());
        officeData.setCountry(pOffice.getCountry());
        officeData.setTerritory(pOffice.getTerritory());

        Office saveOffice  = officeRepository.save(officeData);
        return saveOffice;
    }

    //DELETE ALL
    public void deleteAll(){
        officeRepository.deleteAll();
    }
    
    //DELETE BY ID
    public void deleteById(int id){
        officeRepository.deleteById(id);
    }
}
