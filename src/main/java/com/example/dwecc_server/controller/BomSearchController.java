package com.example.dwecc_server.controller;

import com.example.dwecc_server.model.request.BomSearchRequest;
import com.example.dwecc_server.model.response.MaterialListResponse;
import com.example.dwecc_server.model.response.ProductListResponse;
import com.example.dwecc_server.service.BomSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// BOM Search 기능
@RestController
@RequestMapping("/bomSearch")
@Slf4j
public class BomSearchController {
    @Autowired
    BomSearchService bomSearchService;

/*
BOM Search 기능(모델명 검색 데이터)
description: 제품명 검색 후 해당 모델 리스트 출력
input: BomSearchRequest, (String)BomSearchRequest.getProduct -> 필수
output: ProductListResponse, 이후 (ArrayList<String>)ProductListResponse.productArrayList-> 필수
*/
    @RequestMapping("/product")
    public ProductListResponse getProductList(@RequestBody BomSearchRequest request){

        System.out.println("getProductList is invoked");
        System.out.println("request value: " + request);
        System.out.println("request product value: " + request.getProduct());
        System.out.println("request model value: " + request.getModel());
        System.out.println("request spec value: " + request.getSpec());

        return bomSearchService.findProductByName(request);
    }

/*
BOM Search 기능(모델 선택 후 자재리스트 출력)
description: product, model, spec 데이터 input 투입 후 그에 대한 자재 데이터를 ArrayList<Material> type으로 리턴.
input: BomSearchReques, BomSearchRequest's all field
output: MaterialListResponse, (ArrayList<Material>)MaterialListResponse.getMaterialArrayList
*/
    @RequestMapping("/material")
    public MaterialListResponse getMaterialList(@RequestBody BomSearchRequest request){

        System.out.println("getProductList is invoked");
        System.out.println("request value: " + request);
        System.out.println("request product value: " + request.getProduct());
        System.out.println("request model value: " + request.getModel());
        System.out.println("request spec value: " + request.getSpec());

        return bomSearchService.findMaterialsByName(request);
    }
}