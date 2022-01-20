package com.wetcoding.simpletodo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @SequenceGenerator(name = "id_sequence_generator", sequenceName = "id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "id_sequence_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    private Date created;

    private Date modified;

    @Version
    private Integer version;


    @PrePersist
    public void onInit() {
        this.created = new Date();
        this.modified = created;
    }

    @PreUpdate
    public void onUpdate() {
        this.modified = new Date();
    }

}
