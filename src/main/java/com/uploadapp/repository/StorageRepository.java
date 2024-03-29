package com.uploadapp.repository;

import com.uploadapp.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByName(String filename);
    Optional<ImageData> findByNameAndType(String name, String type);
}
