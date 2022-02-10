package com.inpt.spring.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inpt.spring.note.models.UploadedFile;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long>{

}
