package com.example.application.services;

import com.example.application.entities.InfoEntity;
import com.example.application.repositories.InfoRepository;
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
        return ParseText(info);
    }

    public Long putFile(MultipartFile fileUpload) throws IOException {
        var bytes = fileUpload.getBytes();
        var entity = new InfoEntity();
        entity.setContext(bytes);
        infoRepository.save(entity);
        return entity.getId();
    }

    private static String ParseText(String[] text) {
        StringBuilder result = new StringBuilder();
        int fixedSectionNumber = 0;
        for (var line : text) {
            int sectionNumber = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.toCharArray()[i] != '#') break;
                sectionNumber++;
            }

            if (sectionNumber != 0) {
                fixedSectionNumber = sectionNumber;
                result.append("\n").append("\t".repeat(sectionNumber - 1))
                        .append(line.substring(sectionNumber)).append("\n");
            } else
                result.append("\t".repeat(fixedSectionNumber)).append(line).append("\n");
        }
        return result.substring(1);
    }
}
