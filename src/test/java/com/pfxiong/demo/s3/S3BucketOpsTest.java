package com.pfxiong.demo.s3;


import org.junit.Test;
import software.amazon.awssdk.services.s3.S3Client;

public class S3BucketOpsTest {

    S3BucketOps s3BucketOps = new S3BucketOps();

    @Test
    public void listBucketTest() {
        S3Client s3 = S3Helper.getS3Client();
        s3BucketOps.listBucket(s3);
    }

    @Test
    public void listObjectTest() {
        String bucketName = "hfc14-a13test-bucket01";
        S3Client s3 = S3Helper.getS3Client();
        s3BucketOps.listBucketObjects(s3, bucketName);
    }
}