package com.sebastian.clientsappbackend.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    Resource load(String fileName) throws MalformedURLException;

    String save(MultipartFile file) throws IOException;

    boolean delete(String fileName);

    Path getPath(String fileName);

}
