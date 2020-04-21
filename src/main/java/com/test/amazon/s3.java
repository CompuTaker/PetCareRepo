package com.test.amazon;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Controller
public class s3 {
	private static final String BUCKET_NAME = "petcare2020";
    private static final String ACCESS_KEY = "AKIA5MJ53SQDXBHH42PY";
    private static final String SECRET_KEY = "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";
    private static AmazonS3 amazonS3;//인스턴스초기화
 
    public s3() {
    	//인증객체 생성
        AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        amazonS3 = new AmazonS3Client(awsCredentials);
    }
 
    public static void uploadFile(MultipartFile multipartfile) throws IOException {
        if (amazonS3 != null) {
        	
            try {
            	ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(MediaType.IMAGE_PNG_VALUE);
//                metadata.setContentLength(file.getSize());
            	
                // 파일 업로드를 위한 request 객체 생성
                PutObjectRequest putObjectRequest =
                        new PutObjectRequest(BUCKET_NAME, multipartfile.getOriginalFilename(), multipartfile.getInputStream(), metadata);//BUCKET_NAME + "생성 될 폴더 이름", 파일 원본이름, File 바이너리 데이터
                
                // file permission(공개 조회가 가능 하도록 public Read 로 설정)
                putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);            
                
                // upload file
                amazonS3.putObject(putObjectRequest); 
 
            } catch (AmazonServiceException ase) {
                ase.printStackTrace();
            } finally {
                amazonS3 = null;
            }
        }
    }
}