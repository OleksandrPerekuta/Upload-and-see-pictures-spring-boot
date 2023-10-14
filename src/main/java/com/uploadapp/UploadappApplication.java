package com.uploadapp;

import com.uploadapp.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class UploadappApplication {
	private final StorageService service;

	@PostMapping
	public ResponseEntity<String> uploadImage(@RequestParam("image")MultipartFile file){
		String uploadImage;
		try {
			uploadImage = service.uploadImage(file);
		}catch (IOException e){
			uploadImage="error";
		}
		return new ResponseEntity<>(uploadImage, HttpStatus.OK);
	}


	@GetMapping("/{fileName}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName){
		byte[] bytes = service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(bytes);
	}


	public static void main(String[] args) {
		SpringApplication.run(UploadappApplication.class, args);
	}

}
