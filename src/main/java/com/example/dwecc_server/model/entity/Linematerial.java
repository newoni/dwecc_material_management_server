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
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Linematerial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public long materialInfo;

    public String lot;

    public long seq;

    public LocalDateTime expDate;

    public long quantity;

}
