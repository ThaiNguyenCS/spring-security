package com.spring_boot_template.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"})
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @NotNull
    String username;
    @NotNull
    String password;
}
