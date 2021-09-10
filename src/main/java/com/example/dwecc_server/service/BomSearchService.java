package com.example.dwecc_server.service;

import com.example.dwecc_server.model.entity.Product;
import com.example.dwecc_server.model.request.BomSearchRequest;
import com.example.dwecc_server.model.response.ProductListResponse;
import com.example.dwecc_server.model.response.ProductResponse;
import com.example.dwecc_server.repository.LinematerialRepository;
import com.example.dwecc_server.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BomSearchService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    LinematerialRepository linematerialRepository;

    public ProductListResponse findProductByName(BomSearchRequest request){

        ProductListResponse productListResponse = new ProductListResponse();
        ArrayList<ProductResponse> result = new ArrayList<ProductResponse>();
        List<Product> productList = productRepository.findByProduct(request.getProduct());

        for(int i=0; i<productList.size(); i++){

            ProductResponse productResponse = new ProductResponse();
            productResponse.setProduct(productList.get(i).getProduct());
            productResponse.setModel(productList.get(i).getModel());
            productResponse.setSpec(productList.get(i).getSpec());

            result.add(productResponse);
        }
        productListResponse.setProductArrayList(result);

        log.info("BomSearchService/productListResponse: "+ productListResponse);
        return productListResponse;
    }
}