package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.PerformerDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.Performer;
import org.springframework.stereotype.Component;

@Component
public class PerformerAssembler {
    PerformerDto toPerformerDto(Performer performer) {
        PerformerDto performerDto = new PerformerDto();
        performerDto.setId(performer.getId());
        performerDto.setName(performer.getName());
        performerDto.setDescription(performer.getDescription());

        return performerDto;
    }
}
