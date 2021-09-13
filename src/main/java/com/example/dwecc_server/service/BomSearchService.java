package com.example.dwecc_server.service;

import com.example.dwecc_server.model.entity.Material;
import com.example.dwecc_server.model.entity.Relation;
import com.example.dwecc_server.model.entity.Product;
import com.example.dwecc_server.model.request.BomSearchRequest;
import com.example.dwecc_server.model.response.MaterialListResponse;
import com.example.dwecc_server.model.response.ProductListResponse;
import com.example.dwecc_server.model.response.ProductResponse;
import com.example.dwecc_server.repository.LinematerialRepository;
import com.example.dwecc_server.repository.RelationRepository;
import com.example.dwecc_server.repository.MaterialRepository;
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

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    RelationRepository relationRepository;

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

    public MaterialListResponse findMaterialsByName(BomSearchRequest request){
        //검색 값에 맞는 product 구하기
        Product resProduct = productRepository.findByProductAndModelAndSpec(request.getProduct(), request.getModel(), request.getSpec());

        //product에 해당하는 ID 값으로 material2product 값을 List 형태로 받아오기
        log.info("resProduct: " + resProduct);
        List<Relation> relationList = relationRepository.findByProduct(resProduct.getId());

        log.info("resRelation: " + relationList);
        log.info("resRelation size: " + relationList.size());
        //결과 출력용 MaterialListResponse와 Material ArrayList(file값) 만들기
        MaterialListResponse materialListResponse = new MaterialListResponse();
        ArrayList<Material> materialArrayList = new ArrayList<Material>();

        for(int i=0; i<relationList.size(); i++){
            materialArrayList.add(materialRepository.findById(relationList.get(i).getMaterial()).get());
        }
        materialListResponse.setMaterialArrayList(materialArrayList);

        return materialListResponse;
    }
}