package com.pfxiong.demo.s3;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author: xiongpengfei
 * @datetime: 2021/6/25 14:40
 * @description:
 */
public class S3Helper {
    private static final String BUCKET_NAME = "hfc14-a13test-bucket01";

    private static final String END_POINT = "http://s3.nie.netease.com";

    private static final Region REGION = Region.of("HZ");

    public static final AwsCredentialsProvider CREDS = StaticCredentialsProvider.create(AwsBasicCredentials.create("**", "**"));
    //https://github.com/aws/aws-sdk-java-v2/issues/448 参考文档
    private static S3Client s3Client;

    public static S3Client getS3Client() {

        if (s3Client == null) {
            synchronized (S3Client.class) {//简单处理，无需绝对的单例
                try {
                    s3Client = S3Client.builder()
                            .credentialsProvider(S3Helper.CREDS)
                            .endpointOverride(new URI(S3Helper.END_POINT))
                            .region(REGION)
                            .build();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
        return s3Client;
    }
}