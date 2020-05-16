package com.ua.serveping.service.models.domains;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "users")
@Entity
@NoArgsConstructor
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String emailId;

    @Column
    @JsonIgnore
    private String password;

    @Column
    @JsonIgnore
    private Boolean active;

    @Column
    private Timestamp createdAt;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;


    public Users(String username, String emailId, String password, Boolean active, Timestamp createdAt, List<Role> roles) {
        this.username = username;
        this.emailId = emailId;
        this.password = password;
        this.active = active;
        this.createdAt = createdAt;
        this.roles = roles;
    }

}
