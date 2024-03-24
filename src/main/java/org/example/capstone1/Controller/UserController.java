package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.User;
import org.example.capstone1.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {

        return ResponseEntity.status(200).body(userService.getAllUsers());
    }



    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }


    @PutMapping("/update/{Id}")
    public ResponseEntity updateUser(@PathVariable Integer Id , @RequestBody @Valid User user , Errors errors ){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdate = userService.updateUser(Id , user);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        Boolean userDeleted = userService.deleteUser(id);
        if (userDeleted) {
            return ResponseEntity.status(200).body("deleted");
        } else {
            return ResponseEntity.status(400).body("not found");
        }
    }





    //12
    @PostMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer merchantId) {
        String purchaseStatus = userService.buyProduct(userId, productId, merchantId);
        if (purchaseStatus.equals("Product purchased.")) {
            return ResponseEntity.ok("Product purchased");

        } else if (purchaseStatus.equals("Insufficient balance.") || purchaseStatus.equals("Product out of stock.") || purchaseStatus.equals("Invalid user, product, or merchant.")) {
            return ResponseEntity.badRequest().body(purchaseStatus);
        }
        return ResponseEntity.notFound().build();
    }
    // اذا الستوك 0














//extra
@PostMapping("/user/addBalance")
public ResponseEntity addBalance(@RequestBody Integer userId, @RequestBody double amount) {
    userService.addBalance(userId, amount);
    return ResponseEntity.ok("Balance added.");
}

    @PostMapping("/user/refundBalance")
    public ResponseEntity refundBalance(@RequestBody Integer userId, @RequestBody double amount) {
        userService.refundBalance(userId, amount);
        return ResponseEntity.ok("Balance refunded.");
    }
}








