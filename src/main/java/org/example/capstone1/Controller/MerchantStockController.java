package org.example.capstone1.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Service.MerchantStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/merchantStock")
public class MerchantStockController {

    private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ResponseEntity getAllMerchantStocks() {
        return ResponseEntity.status(200).body(merchantStockService.getAllMerchantStocks());
    }



    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock,Errors errors) {
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("MerchantStock added"));
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer Id , @RequestBody @Valid MerchantStock merchantStock , Errors errors ){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = merchantStockService.updateMerchantStock(Id , merchantStock);
        if (isUpdate){
            return ResponseEntity.status(200).body("updated");
        }

        return ResponseEntity.status(400).body("Invalid Id");
    }


    @DeleteMapping("/delete/{Id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id ){

        Boolean isDelete= merchantStockService.deleteMerchantStock(id);
        if(isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id"));
    }


    //11
    @PutMapping("/addStock/{productId}/{merchantId}/{stock}")
    public ResponseEntity addStock(@PathVariable Integer productId, @PathVariable Integer merchantId, @PathVariable Integer stock) {
        merchantStockService.addStock(productId, merchantId, stock);
        return ResponseEntity.ok("Stock added");
    }






}






