package io.github.alanabarbosa.services;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.github.alanabarbosa.config.FileStorageConfig;
import io.github.alanabarbosa.exceptions.FileStorageException;
import io.github.alanabarbosa.exceptions.MyFileNotFoundException;
import io.github.alanabarbosa.model.File;
import io.github.alanabarbosa.repositories.FileRepository;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;
	
	@Autowired
	FileRepository repository;
	
	
	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		Path path = Paths.get(fileStorageConfig.getUploadDir())
				.toAbsolutePath().normalize();
		
		this.fileStorageLocation = path;
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Could not create directory where the uploaded files will be stored!", e);
		}
	}
	
	public String storeFile(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if (filename.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + filename);
			}
			//Path targetLocation = this.fileStorageLocation.resolve(filename);
			//Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
            File fileEntity = new File();
            fileEntity.setFilename(filename);
            fileEntity.setData(file.getBytes());
            repository.save(fileEntity);
            
			return filename;
		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + filename + ". Please try again!", e);
		}
	}	
	
	public Resource loadFileAsResource(String filename) {
	    try {
	        File fileEntity = repository.findByFilename(filename)
	                .orElseThrow(() -> new MyFileNotFoundException("File not found " + filename));
	        
	        // Cria um InputStreamResource a partir dos bytes do arquivo
	        return new InputStreamResource(new ByteArrayInputStream(fileEntity.getData()));
	    } catch (Exception e) {
	        throw new MyFileNotFoundException("File not found " + filename, e);
	    }
	}
}
