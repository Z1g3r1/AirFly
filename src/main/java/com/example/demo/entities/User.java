package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity @Table (name = "users")
public class User implements UserDetails {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    private String username;
    private String password;
    @OneToMany (mappedBy = "user")
    private List<Ticket> tickets;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }
    public User() {}

    public User(String username, String password, String firstName, String lastName, String email, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, List<Ticket> ticket, String role) {
        this.username = username;
        this.password = password;
        this.tickets = ticket;
        this.role = role;
    }
}
