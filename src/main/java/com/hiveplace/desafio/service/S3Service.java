package com.hiveplace.desafio.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private Logger LOGGER = LoggerFactory.getLogger(S3Service.class);
    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public void uploadFile(String localFilePath) {

        try{
            File file = new File(localFilePath);
            LOGGER.info("Iniciando Upload...");
            s3client.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));
            LOGGER.info("Upload finalizado");
        } catch (AmazonServiceException ex) {
            LOGGER.info("AmazonServiceException: " + ex.getErrorMessage());
            LOGGER.info("Status code: " + ex.getErrorCode());
        } catch (AmazonClientException ex) {
            LOGGER.info("AmazonClientException: " + ex.getMessage());
        }

    }

}
