package com.example.dwecc_server.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    String code;
    String name;
    Long boxQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBoxQuantity() {
        return boxQuantity;
    }

    public void setBoxQuantity(Long boxQuantity) {
        this.boxQuantity = boxQuantity;
    }
}
