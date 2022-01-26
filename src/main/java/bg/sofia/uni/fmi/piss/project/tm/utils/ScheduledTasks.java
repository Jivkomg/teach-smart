package bg.sofia.uni.fmi.piss.project.tm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private MicrosoftCoursesConnector microsoftCoursesConnector;

    @Autowired
    public ScheduledTasks(MicrosoftCoursesConnector microsoftCoursesConnector) {
        this.microsoftCoursesConnector = microsoftCoursesConnector;
    }

    @Scheduled(initialDelay = 100, fixedRate = 1000 * 60 * 60)
    public void synchronizeMicrosoft() {
        try {
            System.out.println("Starting synchronizing of courses with microsoft");
            this.microsoftCoursesConnector.syncCoursesFromMicrosoftWithTechSmartCourses();
            System.out.println("Synchronizing of courses with microsoft finished successfully");
        } catch (RuntimeException e) {
            System.out.println("Error during execution of synchronizeMicrosoft method");
        }
    }
}



