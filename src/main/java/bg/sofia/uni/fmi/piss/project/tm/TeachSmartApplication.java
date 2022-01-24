package bg.sofia.uni.fmi.piss.project.tm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TeachSmartApplication {

  public static void main(String[] args) {
    SpringApplication.run(TeachSmartApplication.class, args);
  }

}

