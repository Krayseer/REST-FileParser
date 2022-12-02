package com.example.application.controllers;

import com.example.application.entities.InfoEntity;
import com.example.application.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class InfoController {
    private final InfoService infoService;

    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable Long id){
        return ResponseEntity.ok(infoService.getTextById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile fileUpload) throws IOException {
        return ResponseEntity.ok("Загрузил файл, получить его можно по id: " + infoService.putFile(fileUpload));
    }
}
