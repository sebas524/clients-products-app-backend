package com.sebastian.clientsappbackend.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    private final static String DIRECTORY_UPLOAD = "uploads";

    @Override
    public Resource load(String fileName) throws MalformedURLException {

        Path filePath = getPath(fileName);

        log.info(filePath.toString());

        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() && !resource.isReadable()) {

            // * so if image where to be deleted, the no pic image will show:
            filePath = Paths.get("src/main/resources/static/images").resolve("no-pic.png").toAbsolutePath();

            resource = new UrlResource(filePath.toUri());

            log.error("image could not be loaded." + fileName);

        }
        return resource;

    }

    @Override
    public String save(MultipartFile file) throws IOException {

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");

        Path filePath = getPath(fileName);

        log.info(filePath.toString());

        Files.copy(file.getInputStream(), filePath);
        return fileName;

    }

    @Override
    public boolean delete(String fileName) {
        if (fileName != null && fileName.length() > 0) {
            Path previousImagePath = Paths.get("uploads").resolve(fileName).toAbsolutePath();
            File previosImageFile = previousImagePath.toFile();
            if (previosImageFile.exists() && previosImageFile.canRead()) {
                previosImageFile.delete();
                return true;

            }

        }
        return false;
    }

    @Override
    public Path getPath(String fileName) {
        return Paths.get(DIRECTORY_UPLOAD).resolve(fileName).toAbsolutePath();
    }

}
