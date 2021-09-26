package com.example.dwecc_server.controller;

import com.example.dwecc_server.model.entity.Linematerial;
import com.example.dwecc_server.model.request.MaterialRequest;
import com.example.dwecc_server.model.response.LineMaterialListResponse;
import com.example.dwecc_server.service.LineMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//    창고 자재 관리 관련 기능(입/출고, 재고 확인)
@RestController
@RequestMapping("/material")
public class MaterialManagementController {
    @Autowired
    LineMaterialService lineMaterialService;

/*
    description: 전체 자재 리스트 출력 버튼 클릭시 동작
    input: none
    output: LineMaterialListResponse, (ArrayList<LineMaterialResponse>) LineMaterialService.getLineMaterialResponseArrayList 값을 받아올 것.
 */
    @RequestMapping("/readAll")
    public LineMaterialListResponse readAllLineMaterial(){
        return lineMaterialService.readAllLineMaterial();
    }

/*
    description: QR코드 데이터 입력을 DB에 저장
    input: MaterialRequest, 이후 (String)code, (String)lot, (Long)qty, (Long)seq 가 필수적으로 활용됨
    output: None
 */
    @RequestMapping("/create")
    public void create(@RequestBody MaterialRequest request){
        lineMaterialService.create(request);
    }

/*
    description: QR코드 데이터 입력을 DB에 대조하고, 없을 경우 삭제
    input: MaterialRequest, 이후 (String)code, (String)lot, (Long)qty, (Long)seq 가 필수적으로 활용됨
    output: None
 */
    @RequestMapping("/delete")
    public void delete(@RequestBody MaterialRequest request){
        lineMaterialService.delete(request);
    }
}
