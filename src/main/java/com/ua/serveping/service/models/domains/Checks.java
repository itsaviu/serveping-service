package com.ua.serveping.service.models.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.serveping.service.models.CheckInterval;
import com.ua.serveping.service.models.CheckStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Data
public class Checks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "checkTypeId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private CheckType checkType;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Users users;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private CheckInterval checkInterval;

    @Column
    private String endpoint;

    @Column
    private Timestamp createdAt;

    @Column
    @Enumerated(EnumType.STRING)
    private CheckStatus status;

    @Column
    private Boolean isDelete;

    @OneToMany(mappedBy = "checks")
    @JsonIgnore
    private List<CheckReport> checkReports;

    public Checks(CheckType checkType, Users users, String name, CheckInterval checkInterval, String endpoint, Timestamp createdAt, CheckStatus status, Boolean isDelete) {
        this.checkType = checkType;
        this.users = users;
        this.name = name;
        this.checkInterval = checkInterval;
        this.endpoint = endpoint;
        this.createdAt = createdAt;
        this.isDelete = isDelete;
        this.status = status;
    }
}
