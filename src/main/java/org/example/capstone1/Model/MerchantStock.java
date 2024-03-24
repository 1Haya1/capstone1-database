package org.example.capstone1.Model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {

    @NotNull(message = "must be not empty")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Product ID must not be empty")
    @Column(columnDefinition = "int not null")
    private Integer productId;

    @NotNull(message = "Merchant ID must not be empty")
    @Column(columnDefinition = "int not null")
    private Integer merchantId;

   @NotNull(message = "must be not empty")
   @Size(min = 11, message = "have to be more than 10 at start")
   @Column(columnDefinition = "int not null")
    private Integer stock;

}
