package com.example.dwecc_server.service;

import com.example.dwecc_server.model.entity.Linematerial;
import com.example.dwecc_server.model.entity.Material;
import com.example.dwecc_server.model.request.MaterialRequest;
import com.example.dwecc_server.model.response.LineMaterialListResponse;
import com.example.dwecc_server.model.response.LineMaterialResponse;
import com.example.dwecc_server.repository.LinematerialRepository;
import com.example.dwecc_server.repository.MaterialRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LineMaterialService {
    @Autowired
    LinematerialRepository linematerialRepository;

    @Autowired
    MaterialRepository materialRepository;

/*
description: DB에 입력된 모든 자재 데이터 출력
input: none
output: LineMaterialListResponse, 이 후 LineMaterialResponseArrayList field 활용
*/
    public LineMaterialListResponse readAllLineMaterial(){
//        line material 정보 수집
        List<Linematerial> linematerialList = linematerialRepository.findAll();

        log.info("lineMaterialList, "+linematerialList);

//        결과 리턴용 객체 생성
        LineMaterialListResponse lineMaterialListResponse = new LineMaterialListResponse();
        ArrayList<LineMaterialResponse> lineMaterialResponseArrayList = new ArrayList<LineMaterialResponse>();


//      for 문을 돌며 linematerialArrayList의 값에서 material 데이터 추출
        for(int i=0 ; i<linematerialList.size(); i++){
//          결과 리스트에 추가할 lineMaterialResponse 객체 생성
            LineMaterialResponse lineMaterialResponse = new LineMaterialResponse();

//            lineMaterialResponse의 lot, seq, qty 정보 기재
            lineMaterialResponse.setLot(linematerialList.get(i).getLot());
            lineMaterialResponse.setSeq(linematerialList.get(i).getSeq());

            Long id = linematerialList.get(i).getId();
            String lot = linematerialList.get(i).getLot();
            Long seq = linematerialList.get(i).getSeq();
            Long qty = linematerialList.get(i).getQuantity();

//            lineMaterialResponse component에 material 값 입력
            lineMaterialResponse.setLot(linematerialList.get(i).getLot());
            lineMaterialResponse.setSeq(linematerialList.get(i).getSeq());

//            material 검색 후 추가
            Material material = materialRepository.findById(linematerialList.get(i).getMaterial()).get();
            lineMaterialResponse.setCode(material.getCode());
            lineMaterialResponse.setName(material.getName());
            lineMaterialResponse.setQty(material.getBoxQuantity());

//            lineMaterialResponse 값을 array에 추가
            lineMaterialResponseArrayList.add(lineMaterialResponse);
        }

        lineMaterialListResponse.setLineMaterialResponseArrayList(lineMaterialResponseArrayList);
        return lineMaterialListResponse;
    }

/*
description: QR코드 데이터 입력 후 해당 데이터 입력
input: MaterialRequest, 이 후 code, lot, seq, qty 피수 사용
output: void
 */
    public void create(MaterialRequest request){
        log.info("request value" +request);
        log.info("request code: "+request.getCode());
        log.info("request lot: "+request.getLot());
        log.info("request seq: "+request.getSeq());
        log.info("request qty: "+request.getQty());

//        자재코드를 바탕으로 해당 자재 데이터 받아오기
        Material material = materialRepository.findByCode(request.getCode());

        log.info("material id: " + material.getId());
        log.info("material code: " + material.getCode());
        log.info("matrial name: " + material.getName());

//        받아온 자재 데이터와 입력 데이터를 혼합하여 Linematerial 객체 생성
        Linematerial linematerial = Linematerial.builder()
                .material(material.getId())
                .lot(request.getLot())
                .seq(request.getSeq())
                .quantity(request.getQty())
                .build();

//       생성한 객체를 DB에 저장
        Linematerial newLineMaterial = linematerialRepository.save(linematerial);

        log.info("newLineMaterial.getLot(): " +newLineMaterial.getLot());
        log.info("newLineMaterial.getCode() " + newLineMaterial.getMaterial());
        log.info("newLineMaterial.getSeq() " + newLineMaterial.getSeq());
        log.info("newLineMaterial.getQuantity() " + newLineMaterial.getQuantity());
    }

/*
description: QR코드 데이터 입력 후 해당 데이터 삭제
input: MaterialRequet, 이 후 code, lot, seq, qty 피수 사용
output: void
 */
    public void delete(MaterialRequest request){
        log.info("request value" +request);
        log.info("request code: "+request.getCode());
        log.info("request lot: "+request.getLot());
        log.info("request seq: "+request.getSeq());
        log.info("request qty: "+request.getQty());

//        자재코드를 바탕으로 해당 자재 데이터 받아오기
        Material material = materialRepository.findByCode(request.getCode());

        log.info("material id: " + material.getId());
        log.info("material code: " + material.getCode());
        log.info("matrial name: " + material.getName());

//        받아온 자재 데이터와 입력 데이터를 혼합하여 현재 라인에 있는 데이터 확인
        Linematerial linematerial =linematerialRepository.findByMaterialAndLotAndSeqAndQuantity(material.getId(),request.getLot(), request.getSeq(), request.getQty());

//        데이터 삭제
        linematerialRepository.delete(linematerial);
    }
}
