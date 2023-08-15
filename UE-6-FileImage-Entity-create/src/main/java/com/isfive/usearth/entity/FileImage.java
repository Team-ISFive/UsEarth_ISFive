package com.isfive.usearth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class FileImage {

    @Id
    @GeneratedValue
    private Long id;
    private String originalName;
    private String storedName;
}
