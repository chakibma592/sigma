package com.optimgov.spring.elearning.controllers;

import java.util.Arrays;
import java.util.Optional;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.optimgov.spring.elearning.models.UploadedFile;
import com.optimgov.spring.elearning.models.User;
import com.optimgov.spring.elearning.payload.response.MessageResponse;
import com.optimgov.spring.elearning.repository.UploadedFileRepository;
import com.optimgov.spring.elearning.repository.UserRepository;
import com.optimgov.spring.elearning.upload.FilesStorageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/file")
public class FilesController {
	 @Autowired
	  FilesStorageService storageService;
	 @Autowired
	  UploadedFileRepository fileRepository;
	 @Autowired
	    private UserRepository userRepository;
	 
	  @PostMapping("/upload")
	  public ResponseEntity<UploadedFile> uploadFiles(@RequestParam("files") MultipartFile files, @RequestParam("userid") Long userid) {
	    String message = "";
	    UploadedFile file=null;
	    try {
	    	//Optional<User> userData = userRepository.findById(userid);
	    	//User user=userData.get();
	     // Arrays.asList(files).stream().forEach(file -> {
	        storageService.save(files);
	       // fileNames.add(file.getOriginalFilename());
	      //});
	      file = new UploadedFile();
	        file.setFileurl(userid+""+files.getOriginalFilename());
	        fileRepository.save(file);
	        //user.setFileid(file);
	      message = "successful!";
	      return new ResponseEntity<>(file,HttpStatus.OK);
			
	    } catch (Exception e) {
	      message = "Fail to upload files!";
	      return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
	    }
	  }

}
