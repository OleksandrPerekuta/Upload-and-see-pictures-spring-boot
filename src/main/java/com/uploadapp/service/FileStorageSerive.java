package com.uploadapp.service;

import com.uploadapp.entity.FileData;
import com.uploadapp.repository.FileDataRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileStorageSerive {
    private final String FILE_PATH= "/Users/alex/IdeaProjects/Upload-and-see-pictures-spring-boot/src/main/resources/database/images/";
    private FileDataRepository fileDataRepository;

    public String uploadImageToFileSystem(MultipartFile file)throws IOException{
        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(FILE_PATH+file.getOriginalFilename()).build());

        file.transferTo(new File(FILE_PATH+file.getOriginalFilename()));
        if (fileData != null) {
            return "file is uploaded successfully";
        }
        return null;
    }
    public byte[] downloadFile(String fileName) throws IOException {
        Optional<FileData> file = fileDataRepository.findFileByName(fileName);
        String filePath = file.get().getFilePath();
        byte[] image = Files.readAllBytes(new File(filePath).toPath());
        return image;
    }



}
