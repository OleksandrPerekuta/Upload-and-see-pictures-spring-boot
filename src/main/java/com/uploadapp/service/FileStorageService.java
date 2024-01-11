package com.uploadapp.service;

import com.uploadapp.entity.FileData;
import com.uploadapp.repository.FileDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileStorageService {
    private static final String FILE_PATH= "src/main/resources/database/images/";
    private FileDataRepository fileDataRepository;

    public String uploadImageToFileSystem(MultipartFile file)throws IOException{
        Optional<FileData> existingFileData = fileDataRepository.findByNameAndType(file.getOriginalFilename(), file.getContentType());


        File destFile=new File(FILE_PATH);
        if (existingFileData.isPresent()) {
            FileData existingFile = existingFileData.get();
            existingFile.setFilePath(FILE_PATH + file.getOriginalFilename());
            file.transferTo(new File(destFile.getAbsolutePath() + File.separator + file.getOriginalFilename()));
            fileDataRepository.save(existingFile);
            return "File content updated successfully";
        } else {
            FileData fileData = fileDataRepository.save(FileData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .filePath(FILE_PATH + file.getOriginalFilename()).build());

            file.transferTo(new File(destFile.getAbsolutePath() + File.separator + file.getOriginalFilename()));

            if (fileData != null) {
                return "File is uploaded successfully";
            }
        }
        return null;
    }
    public byte[] downloadFile(String fileName) throws IOException {
        Optional<FileData> file = fileDataRepository.findFileByName(fileName);
        String filePath = file.get().getFilePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }



}
