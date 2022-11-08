package com.Jyoti.blog.BlogappApi.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//for uploading images
public interface FileService {
    String uploadImage(String path, MultipartFile file) throws IOException;
    InputStream getResource(String path,String fileName) throws FileNotFoundException;
}
