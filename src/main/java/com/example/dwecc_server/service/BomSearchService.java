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


/*
description: 제품명을 입력 받고 해당하는 productList 출력
input: BomSearchRequest, 이 후 BomSearchRequest.getProduct() 필요
output: ProductListResponse, 이후 product, model, spec 활용
*/
    public ProductListResponse findProductByName(BomSearchRequest request){

//        결과값 리턴을 위한 객체 생성
        ProductListResponse productListResponse = new ProductListResponse();
        ArrayList<ProductResponse> result = new ArrayList<ProductResponse>();

//        repository에서 해당 값을 List 타입으로 모두 검색
        List<Product> productList = productRepository.findByProduct(request.getProduct());

//        반복문을 돌며 List<Product> -> ArrayList<ProductResponse> 로 형변환
        for(int i=0; i<productList.size(); i++){
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProduct(productList.get(i).getProduct());
            productResponse.setModel(productList.get(i).getModel());
            productResponse.setSpec(productList.get(i).getSpec());

            result.add(productResponse);
        }

//        형변환한 데이터를 출력용 객체에 추가
        productListResponse.setProductArrayList(result);

        log.info("BomSearchService/productListResponse: "+ productListResponse);
        return productListResponse;
    }

/*
description: product, model, spec 정보를 받아와서, 해당하는 자재 데이터 돌려줌
input: BomSearchRequest, 이 후 product, model, spec field 필수
output: MaterialListResponse
*/
    public MaterialListResponse findMaterialsByName(BomSearchRequest request){
        log.info("request product: " + request.getProduct());
        log.info("request model: " + request.getModel());
        log.info("request spec: " + request.getSpec());

        //결과 출력용 객체 생성
        MaterialListResponse materialListResponse = new MaterialListResponse();
        ArrayList<Material> materialArrayList = new ArrayList<Material>();

        //검색 값에 맞는 product 구하기
        Product resProduct = productRepository.findByProductAndModelAndSpec(request.getProduct(), request.getModel(), request.getSpec());

        //product에 해당하는 ID 값으로 관련 자제 데이터 List 형태로 받아오기
        log.info("resProduct: " + resProduct);
        List<Relation> relationList = relationRepository.findByProduct(resProduct.getId());

        log.info("resRelation: " + relationList);
        log.info("resRelation size: " + relationList.size());

//        for 문을 돌며 자재/제품 관계에서 해당 자재 정보 추출
        for(int i=0; i<relationList.size(); i++){
            materialArrayList.add(materialRepository.findById(relationList.get(i).getMaterial()).get());
        }
        materialListResponse.setMaterialArrayList(materialArrayList);

        return materialListResponse;
    }
}