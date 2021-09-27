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
}
