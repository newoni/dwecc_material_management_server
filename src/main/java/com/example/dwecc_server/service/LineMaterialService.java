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

//    line의 모든 자재 리스트 출력
    public LineMaterialListResponse readAllLineMaterial(){
//        line material 정보 수집
        List<Linematerial> linematerialList = linematerialRepository.findAll();

        log.info("lineMaterialList, "+linematerialList);

//        결과 리턴용 객체 생성
        LineMaterialListResponse lineMaterialListResponse = new LineMaterialListResponse();
//            객체의 setter를 위한 ArrayList 생성
        ArrayList<LineMaterialResponse> lineMaterialResponseArrayList = new ArrayList<LineMaterialResponse>();


//      material 정보 수집 과정
        for(int i=0 ; i<linematerialList.size(); i++){
//            lineMaterialResponse 객체 생성
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

    public void create(MaterialRequest request){
        log.info("request value" +request);
        log.info("request code: "+request.getCode());
        log.info("request lot: "+request.getLot());
        log.info("request seq: "+request.getSeq());
        log.info("request qty: "+request.getQty());

        Material material = materialRepository.findByCode(request.getCode());

        log.info("material id: " + material.getId());
        log.info("material code: " + material.getCode());
        log.info("matrial name: " + material.getName());

        Linematerial linematerial = Linematerial.builder()
                .material(material.getId())
                .lot(request.getLot())
                .seq(request.getSeq())
                .quantity(request.getQty())
                .build();

        Linematerial newLineMaterial = linematerialRepository.save(linematerial);

        log.info("newLineMaterial.getLot(): " +newLineMaterial.getLot());
        log.info("newLineMaterial.getCode() " + newLineMaterial.getMaterial());
        log.info("newLineMaterial.getSeq() " + newLineMaterial.getSeq());
        log.info("newLineMaterial.getQuantity() " + newLineMaterial.getQuantity());
    }

    public void delete(MaterialRequest request){
        log.info("request value" +request);
        log.info("request code: "+request.getCode());
        log.info("request lot: "+request.getLot());
        log.info("request seq: "+request.getSeq());
        log.info("request qty: "+request.getQty());

        Material material = materialRepository.findByCode(request.getCode());

        log.info("material id: " + material.getId());
        log.info("material code: " + material.getCode());
        log.info("matrial name: " + material.getName());

        Linematerial linematerial =linematerialRepository.findByMaterialAndLotAndSeqAndQuantity(material.getId(),request.getLot(), request.getSeq(), request.getQty());

        linematerialRepository.delete(linematerial);
    }
}
