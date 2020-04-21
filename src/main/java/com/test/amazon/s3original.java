/***
package com.test.amazon;

import java.io.File;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class s3original {
	private static final String BUCKET_NAME = "petcare2020";
	private static final String ACCESS_KEY = "AKIA5MJ53SQDXBHH42PY";
	private static final String SECRET_KEY = "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";
	private AmazonS3 amazonS3;

	public s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        amazonS3 = new AmazonS3Client(awsCredentials);
    }

	public void uploadFile(File file) {
        if (amazonS3 != null) {
            try {
                PutObjectRequest putObjectRequest = ***/
//                        new PutObjectRequest(BUCKET_NAME/*+ "/sub_dir_name"/*sub directory*/, file.getName(), file);
//                putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead); // file permission
//                amazonS3.putObject(putObjectRequest); // upload file
//            } catch (AmazonServiceException ase) {
//                ase.printStackTrace();
//            } finally {
//                amazonS3 = null;
//            }
//        }
//    } // 출처:https:// dwfox.tistory.com/57 [DWFOX]
//}
