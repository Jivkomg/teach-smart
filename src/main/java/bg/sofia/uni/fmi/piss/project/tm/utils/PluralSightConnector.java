package bg.sofia.uni.fmi.piss.project.tm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PluralSightConnector {
//    private RestTemplate REST_TEMPLATE;
//    private HttpEntity<String> ENTITY;

    private static final String GET_COURSES_FROM_PLURALSIGHT = "http://api.pluralsight.com/api-v0.9/courses";


    public void getGetCoursesFromPluralsight() {
//        HttpHeaders headers = new HttpHeaders();
//        ENTITY = new HttpEntity<>(null, headers);
//        ResponseEntity<Object[]> deploymentPlans = REST_TEMPLATE.exchange(GET_COURSES_FROM_PLURALSIGHT,
//            HttpMethod.GET,
//            ENTITY,
//            Object[].class);
//        if (deploymentPlans.getBody() == null) {
//            throw new RuntimeException("Could not get deployment project metadata from bamboo, response was null");
//        }

        String requestUrl = "http://api.pluralsight.com/api-v0.9/courses";
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            if (url != null) {
                in = url.openStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.copy(in, Paths.get("C:\\Users\\zhivkogeorgiev\\Downloads\\Courses.csv"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("finished!");
    }
}
