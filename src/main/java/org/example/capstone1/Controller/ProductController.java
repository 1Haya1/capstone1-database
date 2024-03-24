package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts(){
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }


@PostMapping("/add")
public ResponseEntity addProduct(@RequestBody @Valid  Product product, Errors errors){
    if (errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    productService.addProduct(product);
    return ResponseEntity.status(200).body("added");
}



    @PutMapping("/update/{Id}")
    public ResponseEntity  updateProduct(@PathVariable Integer Id, @RequestBody @Valid Product product, Errors errors) {

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdate = productService.updateProduct(Id , product);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("updated"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }


    @DeleteMapping("/delete/{Id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id ){
        Boolean isDelete= productService.deleteProduct(id);
        if(isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("wrong id"));
    }









    //Extra
    @PostMapping("/addR/{productId}/rating")
    public ResponseEntity addRating(@PathVariable Integer productId, @RequestBody @Valid Integer rating,Errors errors) {
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addRatingForProduct(productId, rating);
        productService.calculateAverageRating(productId);
        return ResponseEntity.ok("Rating added.");
    }

    @GetMapping("/getR/{productId}/ratings")
    public ResponseEntity getAllRatings(@PathVariable Integer productId) {
        List<Integer> ratings = productService.getAllRatingsForProduct(productId);
        return ResponseEntity.ok(ratings);
    }



    @PostMapping("/{productId}/comments")
    public ResponseEntity addCommentForProduct(@PathVariable Integer productId, @RequestBody @Valid String comment,Errors errors) {
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addCommentForProduct(productId, comment);
        return ResponseEntity.ok("comment added.");
    }







}



















