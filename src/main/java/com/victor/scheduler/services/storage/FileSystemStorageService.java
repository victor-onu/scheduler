package com.victor.scheduler.services.storage;

import com.victor.scheduler.config.FileStorageProperties;
import com.victor.scheduler.exceptions.FileStorageException;
import com.victor.scheduler.exceptions.MyFileNotFoundException;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.UrlResource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;


@Primary
@Service
public class FileSystemStorageService implements FileStorageService {

	private FileStorageProperties fileStorageProperties;

//	@Value("${file.upload-dir}")
//	String uploadDir;

	public FileSystemStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageProperties = fileStorageProperties;
	}

	@Override
	public String storeFile(File file) {
		return storeFile(file, "");
	}

	@Override
	public String storeFile(File file, String location) {

		Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + File.separator + location)
				.toAbsolutePath().normalize();
		try {
			if (!Files.exists(fileStorageLocation)) {
				Files.createDirectories(fileStorageLocation);
			}
		} catch (IOException ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getName());
		InputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			// Copy file to the target location (Replacing existing file with the same name)
			Files.copy(fileInputStream, fileStorageLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
			file.delete();
			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		} finally {
			file.delete();
			try {
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean deleteFile(String filename) {

		Path fileStorageLocation = Paths.get(filename).toAbsolutePath().normalize();
		// try {
		return FileUtils.deleteQuietly(fileStorageLocation.toFile());
		/*
		 * } catch (IOException e) { e.printStackTrace(); } return false;
		 */
	}

	@Override
	public boolean fileExists(String filename) {
		Path fileStorageLocation = Paths.get(filename).toAbsolutePath().normalize();
		return Files.exists(fileStorageLocation);
	}

	@Override
	public void emptyDir(String dirName) {
		Path uploadLocation = Paths.get(dirName).toAbsolutePath().normalize();
		try {
			FileUtils.cleanDirectory(uploadLocation.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createDirectory(String dir) {
		Path uploadLocation = Paths.get(dir).toAbsolutePath().normalize();
		try {
			if (!Files.exists(uploadLocation)) {
				Files.createDirectories(uploadLocation);
			}
		} catch (IOException ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}

	}

	@Override
	public String storeFile(MultipartFile file) {
		return storeFile(file, "");
	}

	@Override
	public String storeFile(MultipartFile file, String location) {

		Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + File.separator + location)
				.toAbsolutePath().normalize();

		System.out.println(fileStorageLocation.toString());
		try {
			if (!Files.exists(fileStorageLocation)) {
				Files.createDirectories(fileStorageLocation);
			}
		} catch (IOException ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			// Copy file to the target location (Replacing existing file with the same name)
			Files.copy(file.getInputStream(), fileStorageLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/uploads/")
					.path(fileName)
					.toUriString();
			return fileDownloadUri;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		} finally {
			
		}
	}

	@Override
	public Resource loadFileAsResource(String fileName) {


		try {
			//Path fileStorageLocation = Paths.get(fileName).toAbsolutePath().normalize();
			Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + File.separator + fileName)
					.toAbsolutePath().normalize();

			Resource resource = new UrlResource(fileStorageLocation.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

}
