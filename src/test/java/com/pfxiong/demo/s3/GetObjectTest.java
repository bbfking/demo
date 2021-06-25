package com.pfxiong.demo.s3;

import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;
import java.io.IOException;

public class GetObjectTest extends TestCase {
    GetObject getObject = new GetObject();


    @Test
    public void testGetURL() {
        String bucketName = "hfc14-a13test-bucket01";
        String keyName = "12908.log";;
        S3Client s3 = S3Helper.getS3Client();
        System.out.println(getObject.getURL(s3,bucketName,keyName));
    }

    @Test
    public void testGetObject() throws IOException {
        String bucketName = "hfc14-a13test-bucket01";
        String keyName = "popo.db";;
        S3Client s3 = S3Helper.getS3Client();
        long t1 = System.currentTimeMillis();
        byte[] data = getObject.getObjectBytes(s3, bucketName, keyName);
        System.out.println("耗时" + (System.currentTimeMillis() - t1));
        FileUtils.writeByteArrayToFile(new File("D:\\s3Test\\129081.log"), data, false);
    }

    @Test
    public void testGetContentType() {
        String bucketName = "hfc14-a13test-bucket01";
        String keyName = "12908.log";;
        S3Client s3 = S3Helper.getS3Client();
        System.out.println(getObject.getContentType(s3, bucketName, keyName));
    }
}