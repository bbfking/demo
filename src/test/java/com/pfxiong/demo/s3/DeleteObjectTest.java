package com.pfxiong.demo.s3;

import org.junit.Test;
import software.amazon.awssdk.services.s3.S3Client;

public class DeleteObjectTest{

    private DeleteObject deleteObject = new DeleteObject();


    @Test
    public void deleteTest() {
        String bucketName = "hfc14-a13test-bucket01";
        String keyName = "popo.db";;
        S3Client s3 = S3Helper.getS3Client();
        deleteObject.deleteBucketObjects(s3, bucketName, keyName);
    }

}