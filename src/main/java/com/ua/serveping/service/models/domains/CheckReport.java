package com.ua.serveping.service.models.domains;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "checks_report")
@Entity
@NoArgsConstructor
@Data
public class CheckReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "checkId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Checks checks;

    @Column
    private Long responseTime;

    @Column
    private Long responseStatus;

    @Column
    private Boolean isAvailable;

    @Column
    private Boolean isDelete;

    @Column
    private Timestamp createdAt;

    public CheckReport(Checks checks, Long responseTime, Long responseStatus, Boolean isAvailable, Timestamp createdAt) {
        this.checks = checks;
        this.responseTime = responseTime;
        this.responseStatus = responseStatus;
        this.isAvailable = isAvailable;
        this.isDelete = false;
        this.createdAt = createdAt;
    }
}
