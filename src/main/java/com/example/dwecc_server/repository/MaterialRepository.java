package com.example.dwecc_server.repository;

import com.example.dwecc_server.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
/*
description: 자재 코드를 입력받고 해당하는 데이터 출력
input: (String) 자재 코드 값
output: Mateiral
 */
    Material findByCode(String code);
}
