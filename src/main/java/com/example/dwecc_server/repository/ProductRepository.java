package com.example.dwecc_server.repository;

import com.example.dwecc_server.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
/*
description: 자재명 입력 후 해당하는 List<Product> 출력
input: (String) 자재명
example) US4

output: List<Product>
 */
    List<Product> findByProduct(String productName);

/*
description: 자재 코드를 입력받고 해당하는 데이터 출력
input: (String) 자재 코드 값
output: Mateiral
 */
    Product findByProductAndModelAndSpec(String product, String model, String spec);
}
