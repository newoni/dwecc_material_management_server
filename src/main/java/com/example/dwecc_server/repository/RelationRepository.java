package com.example.dwecc_server.repository;

import com.example.dwecc_server.model.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Long> {
/*
description: 제품 id 를 입력받고, 해당하는 (product idx, material idx) table value 모두 return
input: (String) 제품 id 값
output: (product idx, material idx) table value
*/
    List<Relation> findByProduct(Long productIdx);
}
