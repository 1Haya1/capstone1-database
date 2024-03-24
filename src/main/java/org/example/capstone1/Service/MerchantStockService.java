package org.example.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.MerchantStock;

import org.example.capstone1.Reopsitory.MerchantStockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {


   private final MerchantStockRepository merchantStockRepository;

    public List<MerchantStock> getAllMerchantStocks() {

        return merchantStockRepository.findAll();
    }


    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStockRepository.save(merchantStock);
    }

    public Boolean updateMerchantStock(Integer id , MerchantStock merchantStock){

        MerchantStock s=merchantStockRepository.getById(id);

        if(s==null){
            return false;
        }

        s.setProductId(merchantStock.getProductId());
        s.setMerchantId(merchantStock.getMerchantId());
        s.setStock(merchantStock.getStock());

        merchantStockRepository.save(s);

        return true;
    }



    public Boolean deleteMerchantStock(Integer id){

        MerchantStock s=merchantStockRepository.getById(id);

        if(s==null){
            return false;
        }
        merchantStockRepository.delete(s);
        return true ;
    }



    //11
    public void addStock(Integer productId, Integer merchantId, Integer stock) {
        // البحث عن مخزون التاجر
        MerchantStock merchantStock = getMerchantStockByProductIdAndMerchantId(productId, merchantId);
        if (merchantStock != null) {
            // إذا وجد مخزون موجود، زيادة كمية المخزون
            merchantStock.setStock(merchantStock.getStock() + stock);
        } else {
            // إذا لم يوجد مخزون، إنشاء مخزون جديد
            merchantStockRepository.save(new MerchantStock(null, productId, merchantId, stock));
        }
    }

    // البحث عن مخزون التاجر بواسطة معرف المنتج ومعرف التاجر
    public MerchantStock getMerchantStockByProductIdAndMerchantId(Integer productId, Integer merchantId) {
        MerchantStock merchantStock = merchantStockRepository.getMerchantStockByProductIdAndMerchantId(productId, merchantId);
        if (merchantStock != null) {
            return merchantStock;
        }
        return null;
    }


}













