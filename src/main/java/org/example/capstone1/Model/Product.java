package org.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {



    @NotNull(message = "must be not empty")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "must be not empty")
    @Size(min = 4,message = "have to be more than 3 length long")
    @Column(columnDefinition ="varchar(10) check (length(name)>4) not null" )
    private String name;

    @NotNull(message = "must be not empty")
    @Positive(message = "must be positive number")
    @Column(columnDefinition ="int not null" )
    private Integer price;

    @NotNull(message = "must be not empty")
    @Column(columnDefinition ="int not null" )
    private Integer categoryId;

    @NotNull(message = "must be not empty")
    private List<Integer>ratings;

    @NotNull(message = "must be not empty")
    private List<String>comments;

    @NotNull
    @Column(columnDefinition = "double not null")
    private Double averageRating;
}
