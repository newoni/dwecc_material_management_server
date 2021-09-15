package com.example.dwecc_server.repository;

import com.example.dwecc_server.model.entity.Linematerial;
import com.example.dwecc_server.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinematerialRepository extends JpaRepository<Linematerial, Long> {
    Linematerial findByMaterialInfoAndLotAndSeqAndQuantity(Long materialId, String lot, Long seq, Long qty);
}
