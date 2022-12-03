package com.example.application.services;

import com.example.application.entities.InfoEntity;
import com.example.application.repositories.InfoRepository;
import com.example.application.functions.FileParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class InfoService {
    private final InfoRepository infoRepository;

    @Autowired
    public InfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    public String getTextById(Long id){
        var info = new String(infoRepository.findById(id).get().getContext()).split("\r\n");
        return FileParser.ParseText(info);
    }

    public Long putFile(MultipartFile fileUpload) throws IOException {
        var entity = new InfoEntity(fileUpload.getOriginalFilename(), fileUpload.getBytes());
        infoRepository.save(entity);
        return entity.getId();
    }
}
