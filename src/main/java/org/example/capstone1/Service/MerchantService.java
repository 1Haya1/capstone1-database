package org.example.capstone1.Service;


import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Merchant;
import org.example.capstone1.Reopsitory.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants() {

        return merchantRepository.findAll();
    }


    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public Boolean updateMerchant(Integer id , Merchant merchant){

        Merchant m=merchantRepository.getById(id);

        if(m==null){
            return false;
        }

        m.setName(merchant.getName());

       merchantRepository.save(m);

        return true;
    }

    public Boolean deleteMerchant(Integer id){
        Merchant m=merchantRepository.getById(id);
        if(m==null){
            return false;
        }
       merchantRepository.delete(m);
        return true;
    }








}