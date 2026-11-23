package com.practice.s3ec2springboot;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ProductImageController {

    private final ProductImageService service;

    public ProductImageController(
            ProductImageService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<ProductImage> upload(
            @RequestParam String productName,
            @RequestParam MultipartFile image)
            throws IOException {

        ProductImage result =
                service.upload(productName, image);

        return ResponseEntity.ok(result);
    }
}
