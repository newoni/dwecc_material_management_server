package com.example.dwecc_server.repository;

import com.example.dwecc_server.model.entity.Linematerial;
import com.example.dwecc_server.model.entity.Material;
import com.example.dwecc_server.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinematerialRepository extends JpaRepository<Linematerial, Long> {
/*
description: 자재 창고의 모든 Material list 출력
input: (Long) 자재 id 값, (String) lot 번호, (Long) sequence 번호, (Long) 박스 당 갯수
output: Linematerial
 */
    Linematerial findByMaterialAndLotAndSeqAndQuantity(Long material, String lot, Long seq, Long quantity);
}
