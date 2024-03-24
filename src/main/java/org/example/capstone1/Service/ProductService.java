package org.example.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Product;
import org.example.capstone1.Reopsitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


@Autowired UserService userService;
@Autowired MerchantStockService merchantStockService;

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }



    public void addProduct(Product product) {

        productRepository.save(product);
    }

    public Boolean updateProduct(Integer id , Product product){

        Product p=productRepository.getById(id);

        if(p==null){
            return false;
        }

        p.setCategoryId(product.getCategoryId());
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setRatings(product.getRatings());
        p.setComments(product.getComments());

        productRepository.save(p);

        return true;
    }



    public Boolean deleteProduct(Integer id){

        Product c=productRepository.getById(id);
        if(c==null){
            return false;
        }
        productRepository.delete(c);
        return true;
    }




    //EXtra
    public void addRatingForProduct(Integer productId, Integer rating) {
        Product product = getProductById(productId);
        if (product != null) {
            List<Integer> ratings = product.getRatings();
            ratings.add(rating);
            product.setRatings(ratings);
            productRepository.save(product);
        }
    }

    public void calculateAverageRating(Integer productId) {
        Product product = getProductById(productId);
        if (product != null) {
            List<Integer> ratings = product.getRatings();
            if (!ratings.isEmpty()) {
                int totalRating = 0;
                for (Integer r : ratings) {
                    totalRating += r;
                }
                double averageRating = (double) totalRating / ratings.size();
                product.setAverageRating(averageRating);
                productRepository.save(product);
    }}}


    public List<Integer> getAllRatingsForProduct(Integer productId) {

        return productRepository.findRatingsByProductId(productId);
    }


    public Product getProductById(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }


    public void addCommentForProduct(Integer productId, String comment) {
        Product product = getProductById(productId);
        if (product != null) {
            List<String> comments = product.getComments();
            comments.add(comment);
            product.setComments(comments);
            productRepository.save(product);
        }
    }


}




