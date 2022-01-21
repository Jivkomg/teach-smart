package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.OrganizerDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.Organizer;
import org.springframework.stereotype.Component;

@Component
public class OrganizerAssembler {

    Organizer toOrganizer(OrganizerDto organizerDto) {
        return new Organizer(organizerDto.getId(),
                organizerDto.getName(),
                organizerDto.getDescription());
    }

    OrganizerDto toOrganizerDto(Organizer organizer) {
        OrganizerDto organizerDto = new OrganizerDto();
        organizerDto.setId(organizer.getId());
        organizerDto.setName(organizer.getName());
        organizerDto.setDescription(organizer.getDescription());

        return organizerDto;
    }
}
