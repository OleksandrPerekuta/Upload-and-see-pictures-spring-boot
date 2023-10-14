package com.uploadapp.service;

import com.uploadapp.entity.ImageData;
import com.uploadapp.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;


    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(file.getBytes())
                .build());
            return "file uploaded successfully :  "+imageData.getName();
    }
    public byte[] downloadImage(String filename){
        Optional<ImageData> image = storageRepository.findByName(filename);
        if (image.isPresent()){
            return image.get().getImageData();
        }
        return new byte[0];
    }
}
