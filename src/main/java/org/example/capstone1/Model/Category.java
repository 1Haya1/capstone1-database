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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "must be not null")
    private Integer id;

    @NotNull(message = "must be not null")
    @Size(min = 4,message = "have to be more than 3 length long")
    @Column(columnDefinition = "varchar(10) check (length(name)>3) not null")
    private String name;
}
