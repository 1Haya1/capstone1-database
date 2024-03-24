package org.example.capstone1.Service;

import lombok.RequiredArgsConstructor;

import org.example.capstone1.Model.MerchantStock;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Model.User;
import org.example.capstone1.Reopsitory.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class UserService {

    private final MerchantStockService merchantStockService;
    private final MerchantService merchantService;
    private final ProductService productService;
    private final UserService userService;


    private final UserRepository userRepository;



    public List<User> getAllUsers() {

        return userRepository.findAll();
    }



    public void addUser(User user) {
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id , User user){

        User u=userRepository.getById(id);

        if(u==null){
            return false;
        }

        u.setUsername(user.getUsername());
        u.setRole(user.getRole());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setBalance(user.getBalance());
        userRepository.save(u);

        return true;
    }



    public Boolean deleteUser(Integer id){

        User c=userRepository.getById(id);
        if(c==null){
            return false;
        }
        userRepository.delete(c);
        return true;
    }




    //12
    public String buyProduct(Integer userId, Integer productId, Integer merchantId) {
        User user = userRepository.getById(userId);
        Product product = productService.getProductById(productId);
        MerchantStock merchantStock = merchantStockService.getMerchantStockByProductIdAndMerchantId(productId, merchantId);

        if (user != null && product != null && merchantStock != null) {
            if (merchantStock.getStock() > 0) {
                if (user.getBalance() >= product.getPrice()) {
                    user.setBalance(user.getBalance() - product.getPrice());
                    merchantStock.setStock(merchantStock.getStock() - 1);

                    userRepository.save(user);
                    merchantStockService.updateMerchantStock(merchantStock.getId(), merchantStock);

                    return "Product purchased.";
                } else {
                    return "Insufficient balance.";
                }
            } else {
                return "Product out of stock.";
            }
        } else {
            return "Invalid user, product, or merchant.";
        }
    }





    //extra
    // إضافة المبلغ إلى رصيد المستخدم
    public void addBalance(int userId, double amount) {
        User user = userRepository.getById(userId);
        if (user != null) {
            double currentBalance = user.getBalance();
            user.setBalance(currentBalance + amount);
            userRepository.save(user);
        }
    }




    // استرداد المبلغ المدفوع إلى حساب المستخدم
    public void refundBalance(int userId, double amount) {
        User user = userRepository.getById(userId);
        if (user != null) {
            double currentBalance = user.getBalance();
            user.setBalance(currentBalance + amount);
            userRepository.save(user);
        }}

}




