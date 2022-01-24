package bg.sofia.uni.fmi.piss.project.tm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private PluralSightConnector pluralSightConnector;

    private MicrosoftCoursesConnector microsoftCoursesConnector;

    @Autowired
    public ScheduledTasks(PluralSightConnector pluralSightConnector, MicrosoftCoursesConnector microsoftCoursesConnector) {
        this.pluralSightConnector = pluralSightConnector;
        this.microsoftCoursesConnector = microsoftCoursesConnector;
    }

//    @Scheduled(initialDelay = 100, fixedRate = 1000 * 60 * 60)
    public void synchronizePluralSight() {
        try {
            System.out.println("Starting synchronizing of employees with AD");
            this.pluralSightConnector.getGetCoursesFromPluralsight();
            System.out.println("Synchronizing of employees with AD finished successfully");
        } catch (RuntimeException e) {
            System.out.println("Error was encountered. Sending mail to dev-team");

            System.out.println("Error during execution of Synchronize Employees Scheduled method");
        }
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



