package com.example.dwecc_server.repository;

import com.example.dwecc_server.model.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Long> {
    List<Relation> findByProduct(Long materialIdx);
}
