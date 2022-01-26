package bg.sofia.uni.fmi.piss.project.tm.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Hashtable;

import static bg.sofia.uni.fmi.piss.project.tm.utils.SecurityConstants.USER_DIR;

import bg.sofia.uni.fmi.piss.project.tm.utils.ExceptionMessages;

@Service
public class FileService {

    public ResponseEntity uploadFile(MultipartFile file, String username) {

        try {
            // Create a tmp file to validate before moving in it to the system
            File tempFile = File.createTempFile("tmp_file", ".png");
            Path tempLocation = Paths.get(tempFile.getAbsolutePath());

            Files.copy(file.getInputStream(), tempLocation, StandardCopyOption.REPLACE_EXISTING);

            if (!checkFileForNudity(tempFile.getAbsolutePath())) {
                tempFile.delete();

                return new ResponseEntity<>("Inappropriate content!",
                    HttpStatus.OK);
            }

            String uploadDir = USER_DIR + username;

            Path copyLocation = Paths.get(uploadDir + File.separator + "profile_pic.jpg");

            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);



        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ExceptionMessages.ERROR_PIC,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Your picture is uploaded successfully!",
            HttpStatus.OK);
    }

    private boolean checkFileForNudity(String absolutePathToFile) {
        String key = "UyactQyBMFoFbEfe1l2in6zWCvFuncdg";
        boolean isOk = true;
        try {
            Hashtable<String, String> requestParams = new Hashtable<>();
//            requestParams.put("format", "json");
//            requestParams.put("api_key", key);
            String url = "www.picpurify.com/analyse/1.1";
            String path = "/?API_KEY=" + key + "&task=porn_moderation" +  "&file_image=@" + absolutePathToFile;

            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(url, 80));

            ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
            byteBuffer.clear();
            String request = "POST" + path + " HTTP/1.1\n" + "Host: " + url + "\n\n";
            byteBuffer.put(request.getBytes());
            byteBuffer.flip();

            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }

            byteBuffer.clear();

            String output;
            while (true) {
                int cnt = socketChannel.read(byteBuffer);
                if (cnt > 10) {
                    output = new String(byteBuffer.array());
                    break;
                }
                byteBuffer.clear();
            }

            if (!output.contains("OK")) {
                isOk = false;
            }
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isOk;
    }
}
