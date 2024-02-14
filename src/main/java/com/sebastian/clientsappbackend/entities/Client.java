package com.sebastian.clientsappbackend.entities;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "first name cannot be left blank.")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "last name cannot be left blank.")
    @Column(name = "last_name")
    private String lastName;
    @NotBlank
    @Email(message = "email must contain @.")
    @Column(unique = true)
    private String email;
    // @Column(name = "created_at", nullable = false, updatable = false)
    // @CreationTimestamp
    // private Date createdAt;

    @NotNull(message = "a date must be given.")
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    private String image;

}
