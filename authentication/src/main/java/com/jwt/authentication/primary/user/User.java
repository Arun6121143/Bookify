package com.jwt.authentication.primary.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwt.authentication.primary.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Table(name="user_tbl")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Book> userBookList;
}