package com.uploadapp.repository;

import com.uploadapp.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Optional;

@Repository
public interface FileDataRepository extends JpaRepository<FileData,Long> {
    Optional<FileData> findFileByName(String fileName);
}
