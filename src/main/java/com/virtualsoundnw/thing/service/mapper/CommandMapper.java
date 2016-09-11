package com.virtualsoundnw.thing.service.mapper;

import com.virtualsoundnw.thing.domain.*;
import com.virtualsoundnw.thing.service.dto.CommandDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Command and its DTO CommandDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommandMapper {

    CommandDTO commandToCommandDTO(Command command);

    List<CommandDTO> commandsToCommandDTOs(List<Command> commands);

    Command commandDTOToCommand(CommandDTO commandDTO);

    List<Command> commandDTOsToCommands(List<CommandDTO> commandDTOs);
}
