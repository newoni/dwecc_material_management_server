package com.example.dwecc_server.service;

import com.example.dwecc_server.model.entity.Product;
import com.example.dwecc_server.model.request.BomSearchRequest;
import com.example.dwecc_server.model.response.ProductListResponse;
import com.example.dwecc_server.repository.LinematerialRepository;
import com.example.dwecc_server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BomSearchService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    LinematerialRepository linematerialRepository;
    public ProductListResponse findProductByName(BomSearchRequest request){

        ProductListResponse productListResponse = new ProductListResponse();
        ArrayList<Product> result = new ArrayList<Product>();

        List<Product> productList = productRepository.findByProduct(request.getProduct());

        for(int i=0; i<productList.size(); i++){
            result.add(productList.get(i));
        }
        productListResponse.setProductArrayList(result);

        return productListResponse;
    }
}