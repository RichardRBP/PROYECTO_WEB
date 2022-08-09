package com.ingenieriaweb.springboot.app.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "aulas")
public class Aula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty













    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
