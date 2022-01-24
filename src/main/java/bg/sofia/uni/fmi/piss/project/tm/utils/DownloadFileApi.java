//package bg.sofia.uni.fmi.piss.project.tm.utils;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DownloadFileApi {
//
//    public static String sendPostRequest() throws IOException {
//        String requestUrl = "http://api.pluralsight.com/api-v0.9/courses";
//        URL url = new URL(requestUrl);
//        InputStream in = url.openStream();
//        Files.copy(in, Paths.get("your_filename.pdf"), StandardCopyOption.REPLACE_EXISTING);
//        in.close();
//        System.out.println("finished!");
//    }
//}
