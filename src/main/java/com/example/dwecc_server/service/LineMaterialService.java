package com.example.dwecc_server.service;

import com.example.dwecc_server.model.entity.Linematerial;
import com.example.dwecc_server.model.entity.Material;
import com.example.dwecc_server.model.request.MaterialRequest;
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
    public List<Linematerial> readAllLineMaterial(){
        List<Linematerial> linematerialArrayList = linematerialRepository.findAll();
        return linematerialArrayList;
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
                .materialInfo(material.getId())
                .lot(request.getLot())
                .seq(request.getSeq())
                .quantity(request.getQty())
                .build();

        Linematerial newLineMaterial = linematerialRepository.save(linematerial);

        log.info("newLineMaterial.getLot(): " +newLineMaterial.getLot());
        log.info("newLineMaterial.getCode() " + newLineMaterial.getMaterialInfo());
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

        Linematerial linematerial =linematerialRepository.findByMaterialInfoAndLotAndSeqAndQuantity(material.getId(),request.getLot(), request.getSeq(), request.getQty());

        linematerialRepository.delete(linematerial);
    }
}
