package com.practice.s3ec2springboot;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductImageService {

    private final S3Service s3Service;
    private final ProductImageRepository repository;

    public ProductImageService(
            S3Service s3Service,
            ProductImageRepository repository) {

        this.s3Service = s3Service;
        this.repository = repository;
    }

    public ProductImage upload(
            String productName,
            MultipartFile file) throws IOException {

        // Step 1: Upload image to S3
        String key = s3Service.uploadImage(file);

        try {
            // Step 2: Save image details in RDS
            ProductImage image = new ProductImage();

            image.setProductName(productName);
            image.setOriginalFileName(
                    file.getOriginalFilename());
            image.setObjectKey(key);
            image.setContentType(file.getContentType());

            return repository.save(image);

        } catch (RuntimeException exception) {

            // If database save fails, delete uploaded image
            s3Service.deleteImage(key);

            throw exception;
        }
    }
}
