package bg.sofia.uni.fmi.piss.project.wevip;

import bg.sofia.uni.fmi.piss.project.wevip.dto.CourseDto;
import bg.sofia.uni.fmi.piss.project.wevip.dto.OrganizerDto;
import bg.sofia.uni.fmi.piss.project.wevip.dto.PerformerDto;
import bg.sofia.uni.fmi.piss.project.wevip.dto.TeachSmartUserDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.Course;
import bg.sofia.uni.fmi.piss.project.wevip.model.Organizer;
import bg.sofia.uni.fmi.piss.project.wevip.model.Performer;
import bg.sofia.uni.fmi.piss.project.wevip.model.TeachSmartUser;

public class EntityToDtoMapper {

    public static CourseDto toCourseDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(course.getId());
        courseDto.setName(course.getName());
        courseDto.setType(course.getType());
        courseDto.setStartTime(course.getStartTime());
        courseDto.setDurationHours(course.getDurationHours());
        courseDto.setTicketPrice(course.getTicketPrice());
        courseDto.setTicketsSold(course.getTicketsSold());
        courseDto.setSaleEnd(course.getSaleEnd());
        courseDto.setDescription(course.getDescription());
        courseDto.setPosterLocation(course.getPosterLocation());

        return courseDto;
    }

    public static OrganizerDto toOrganizerDto(Organizer organizer) {
        OrganizerDto organizerDto = new OrganizerDto();
        organizerDto.setId(organizer.getId());
        organizerDto.setName(organizer.getName());
        organizerDto.setDescription(organizer.getDescription());

        return organizerDto;
    }

    public static PerformerDto toPerformerDto(Performer performer) {
        PerformerDto performerDto = new PerformerDto();
        performerDto.setId(performer.getId());
        performerDto.setName(performer.getName());
        performerDto.setDescription(performer.getDescription());

        return performerDto;
    }


    public static TeachSmartUserDto toUserDto(TeachSmartUser user) {
        TeachSmartUserDto userDto = new TeachSmartUserDto();
        userDto.setUserId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
