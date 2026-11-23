# Spring Boot Image Upload to AWS S3 with Metadata Stored in Amazon RDS

## Project Overview

This project demonstrates how to build and deploy a Spring Boot application on an AWS EC2 instance that allows users to upload images.

The application stores the actual image files in **Amazon S3** and saves the image name and S3 object key in **Amazon RDS (MySQL)**.

This approach separates file storage from database storage and provides a scalable way to manage uploaded images.

## Architecture

```text
                 User / Client
                      |
                      | Upload Image
                      v
              Amazon EC2 Instance
              --------------------
              Java
              Maven
              Spring Boot
              --------------------
                 /          \
                /            \
       Upload Image       Save Metadata
           using SDK           |
              |                |
              v                v
         Amazon S3        Amazon RDS
        (Image Files)       (MySQL)
```
<img width="1536" height="1024" alt="S3image" src="https://github.com/user-attachments/assets/1daeb011-d2fc-4c51-95cc-595ba70972df" />


## Technologies Used

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- REST APIs
- Maven

### AWS Services
- **Amazon EC2:** Hosts and runs the Spring Boot application.
- **Amazon S3:** Stores uploaded image files.
- **Amazon RDS (MySQL):** Stores image names and S3 object keys.
- **AWS IAM:** Provides secure access permissions to S3.
- **AWS SDK for Java:** Connects the Spring Boot application with AWS services.

### Database
- MySQL

## Project Workflow

1. The user uploads an image through the Spring Boot REST API.
2. The Spring Boot application running on EC2 receives the image.
3. The application uses AWS SDK for Java to upload the image to Amazon S3.
4. Amazon S3 stores the actual image file.
5. S3 returns the object key identifying the uploaded image.
6. Spring Boot saves the image name, original filename, and S3 object key in Amazon RDS (MySQL).
7. The application can retrieve the image details from RDS and use the S3 object key to access the image.

## AWS Services Configuration

### 1. Amazon EC2
- Launch an EC2 instance.
- Install Java and Maven.
- Deploy and run the Spring Boot application.
- Attach an IAM role to allow secure access to Amazon S3.

### 2. Amazon S3
- Create an S3 bucket.
- Keep the bucket private.
- Upload image files using AWS SDK.
- Store uploaded images using unique object keys.

Example S3 object key:

```text
products/abc123.jpg
```

### 3. Amazon RDS (MySQL)
- Create an Amazon RDS MySQL database.
- Configure database connectivity from EC2.
- Store image metadata in a database table.

Example table: `product_images`

| Column | Description |
|---|---|
| id | Unique image ID |
| product_name | Product name |
| image_name | Original image filename |
| s3_key | S3 object key |

Example record:

| id | product_name | image_name | s3_key |
|---|---|---|---|
| 1 | Samsung Mobile | mobile.jpg | products/abc123.jpg |

## Security Configuration

- Use an IAM role attached to EC2 instead of hardcoding AWS access keys.
- Keep the S3 bucket private.
- Allow EC2 to connect to RDS through security group rules.
- Store database credentials securely using environment variables or AWS Secrets Manager.
- Use presigned URLs or a backend API to provide temporary or controlled image access.

## Key Learning Outcomes

- Deploying a Spring Boot application on Amazon EC2.
- Integrating Spring Boot with Amazon S3 using AWS SDK for Java.
- Connecting Spring Boot to Amazon RDS MySQL.
- Uploading and managing images in S3.
- Storing image metadata in a relational database.
- Understanding IAM roles and AWS security groups.
- Implementing cloud-based file storage architecture.

## Conclusion

This project demonstrates a practical AWS cloud integration using Java Spring Boot, Amazon EC2, Amazon S3, and Amazon RDS.

By storing image files in S3 and their metadata in RDS, the application separates file storage from database management and provides a foundation for building scalable cloud-based applications.

---

**Author:** Manjula G

**Technologies:** Java | Spring Boot | Maven | MySQL | AWS EC2 | AWS S3 | AWS RDS | AWS SDK
