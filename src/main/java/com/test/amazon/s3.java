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
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.test.constants.Constant;
@Controller
public class s3 {
	
	private static AmazonS3 amazonS3;// 인스턴스초기화

	public s3() {
		// 인증객체 생성
		AWSCredentials awsCredentials = new BasicAWSCredentials(Constant.ACCESS_KEY, Constant.SECRET_KEY);
		amazonS3 = new AmazonS3Client(awsCredentials);
	}

	public void uploadFile(MultipartFile multipartfile, String customer_Id) throws IOException {
		if (amazonS3 != null) {
			try {
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(MediaType.IMAGE_PNG_VALUE);
				System.out.println("파일의 원래 이름 : " + multipartfile.getOriginalFilename());
				// 파일 업로드를 위한 request 객체 생성
				PutObjectRequest putObjectRequest = new PutObjectRequest(Constant.BUCKET_NAME, "profile_" + customer_Id,
						multipartfile.getInputStream(), metadata);// BUCKET_NAME, 생성될 파일이름, File 바이너리 데이터,메타데이터

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

	public void deleteFile() {
		amazonS3.deleteObjects(new DeleteObjectsRequest(null));

	}
}