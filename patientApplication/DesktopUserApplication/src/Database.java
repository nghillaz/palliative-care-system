import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javax.swing.*;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class Database {
	
	static AWSCredentials credentials = new BasicAWSCredentials(
			"AKIAJ6ESZAJPDCWD4MOA", 
			"SK1p8jgrSA4t6TlpOgXrX4IW9cVJRjCWSOIu901t");
	static String bucketName			= "rpcareapp";
	static AmazonS3 s3Client = new AmazonS3Client(credentials);
	
	//method for downloading a file from the server of string keyname
	public static File download(String keyName, PrintStream console){
		try {
            System.out.println("Downloading " + keyName + "...");
            S3Object s3object = s3Client.getObject(new GetObjectRequest(
            		bucketName, keyName));
            System.out.println("Content-Type: "  + s3object.getObjectMetadata().getContentType());
            BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
            
            PrintStream outcsv = new PrintStream(new FileOutputStream(keyName));
            System.setOut(outcsv);
            
            while (true) {
                String line = reader.readLine();
                if (line == null) break;

                System.out.println(line);
            }
            System.setOut(console);
            reader.close();
            
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which" +
            		" means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means"+
            		" the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        } catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//a reference to the newly downloaded file
		return new File(keyName);
	}
	
	//method for uploading to the server of string keyname
	public static void upload(String keyName, File file){
		try {
            System.out.println("Uploading " + keyName + "...");
            s3Client.putObject(new PutObjectRequest(bucketName, keyName, file));
            
            //if the upload is successful, tell the user
            JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Completed Successfully.");
            
         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
       	}
		
	}
}
