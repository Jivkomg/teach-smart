package bg.sofia.uni.fmi.piss.project.tm.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bg.sofia.uni.fmi.piss.project.tm.dtos.CourseDto;
import bg.sofia.uni.fmi.piss.project.tm.services.CourseServiceImpl;
import bg.sofia.uni.fmi.piss.project.tm.utils.dtos.MicrosoftCoursesResponse;
import bg.sofia.uni.fmi.piss.project.tm.utils.dtos.Module;

@Service
public class MicrosoftCoursesConnector {
    private RestTemplate REST_TEMPLATE = new RestTemplate();
    private CourseServiceImpl courseService;


    private static final String GET_COURSES_FROM_MICROSOFT_URL = "https://docs.microsoft.com/api/learn/catalog";

    @Autowired
    public MicrosoftCoursesConnector(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    public void syncCoursesFromMicrosoftWithTechSmartCourses() {
        List<Module> modules = getGetCoursesFromMicrosoft().getModules();

        for (Module module : modules) {
            CourseDto courseDto = EntityToDtoMapper.moduleToCourseDto(module);
            try {
                this.courseService.createCourse(courseDto);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public MicrosoftCoursesResponse getGetCoursesFromMicrosoft() {
        HttpEntity<Module> httpEntity= new HttpEntity<>(null, getHttpHeaders());
        ResponseEntity<MicrosoftCoursesResponse> modules = REST_TEMPLATE.exchange(GET_COURSES_FROM_MICROSOFT_URL,
            HttpMethod.GET,
            httpEntity,
            MicrosoftCoursesResponse.class);
        if (modules.getBody() == null) {
            throw new RuntimeException("Could not det response from Microsoft");
        }
        return modules.getBody();

    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}