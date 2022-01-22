package bg.sofia.uni.fmi.piss.project.tm.utils;

import bg.sofia.uni.fmi.piss.project.tm.dtos.CourseDto;
import bg.sofia.uni.fmi.piss.project.tm.dtos.OrganizerDto;
import bg.sofia.uni.fmi.piss.project.tm.dtos.TutorDto;
import bg.sofia.uni.fmi.piss.project.tm.dtos.TeachSmartUserDto;
import bg.sofia.uni.fmi.piss.project.tm.models.Course;
import bg.sofia.uni.fmi.piss.project.tm.models.Organizer;
import bg.sofia.uni.fmi.piss.project.tm.models.Tutor;
import bg.sofia.uni.fmi.piss.project.tm.models.TeachSmartUser;

public class EntityToDtoMapper {

    public static CourseDto toCourseDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(course.getId());
        courseDto.setName(course.getName());
        courseDto.setType(course.getType());
        courseDto.setDurationHours(course.getDurationHours());
        courseDto.setAttendants(course.getAttendants());
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

    public static TutorDto toTutorDto(Tutor tutor) {
        TutorDto tutorDto = new TutorDto();
        tutorDto.setId(tutor.getId());
        tutorDto.setName(tutor.getName());
        tutorDto.setDescription(tutor.getDescription());

        return tutorDto;
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
