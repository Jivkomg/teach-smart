package bg.sofia.uni.fmi.piss.project.wevip.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static bg.sofia.uni.fmi.piss.project.wevip.SecurityConstants.USER_DIR;

import java.net.Socket;
import java.util.concurrent.TimeUnit;
	
@Service
public class FileService {

    public ResponseEntity uploadFile(MultipartFile file, String username) {

		try {
			// Create a tmp file to validate before moving in it to the system
			File tempFile = File.createTempFile("tmp_file", ".png");
			Path tempLocation = Paths.get(tempFile.getAbsolutePath());

			Files.copy(file.getInputStream(), tempLocation, StandardCopyOption.REPLACE_EXISTING);

			if (checkFileForNudity(tempFile.getAbsolutePath())) {
				// delete the nude pic
				tempFile.delete();

				return new ResponseEntity<>("You sent a picture with inappropriate content!",
						HttpStatus.OK);
			}

			String uploadDir = USER_DIR + username;

			Path copyLocation = Paths.get(uploadDir + File.separator + "profile_pic.jpg");

			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

			//delete the temporary file since it's already saved in the system
			tempFile.delete();

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("There was an error processing your picture. Please upload it again.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Your picture is uploaded successfully!",
				HttpStatus.OK);
    }
 
	public boolean checkFileForNudity(String absolutePathToFile) {

		try {	
			Socket socket = new Socket("localhost", 5678);		
			OutputStream oos = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(oos, true);
			writer.println(absolutePathToFile);
			
			InputStream ois = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(ois);
			Integer message = reader.read();

			ois.close();
			oos.close();

			// ascii code for '1' character is 49
			return message.equals(49);
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
}
