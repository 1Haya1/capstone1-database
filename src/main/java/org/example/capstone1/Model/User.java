package org.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {


    @NotNull(message = "must be not empty")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "must be not empty")
    @Size(min = 6,message = "have to be more than 5 length long")
    @Column(columnDefinition ="varchar(10) check (length(username)>6) not null" )
    private String username;

    @NotNull(message = "must be not empty")
    @Size(min = 7,message = "have to be more than 6 length long and contain both characters and digits")
    @Column(columnDefinition ="varchar(10) check (length(password)>7) not null" )
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email
    @Column(columnDefinition ="varchar(20) not null" )
    private String email;

    @NotNull(message = "Role must not be empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be 'Admin' or 'Customer'")
    @Column(columnDefinition ="varchar(9) not null check(role='Admin' or role='Customer')" )
    private String role;

    @NotNull(message = "must be not empty")
    @Positive(message = "Balance must be a positive number")
    @Column(columnDefinition ="double not null" )
    private double balance;

}
