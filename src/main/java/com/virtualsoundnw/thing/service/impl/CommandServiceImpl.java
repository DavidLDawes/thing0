package com.virtualsoundnw.thing.service.impl;

import com.virtualsoundnw.thing.service.CommandService;
import com.virtualsoundnw.thing.domain.Command;
import com.virtualsoundnw.thing.repository.CommandRepository;
import com.virtualsoundnw.thing.service.dto.CommandDTO;
import com.virtualsoundnw.thing.service.mapper.CommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Command.
 */
@Service
@Transactional
public class CommandServiceImpl implements CommandService{

    private final Logger log = LoggerFactory.getLogger(CommandServiceImpl.class);
    
    @Inject
    private CommandRepository commandRepository;

    @Inject
    private CommandMapper commandMapper;

    /**
     * Save a command.
     *
     * @param commandDTO the entity to save
     * @return the persisted entity
     */
    public CommandDTO save(CommandDTO commandDTO) {
        log.debug("Request to save Command : {}", commandDTO);
        Command command = commandMapper.commandDTOToCommand(commandDTO);
        command = commandRepository.save(command);
        CommandDTO result = commandMapper.commandToCommandDTO(command);
        return result;
    }

    /**
     *  Get all the commands.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<CommandDTO> findAll() {
        log.debug("Request to get all Commands");
        List<CommandDTO> result = commandRepository.findAll().stream()
            .map(commandMapper::commandToCommandDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one command by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public CommandDTO findOne(Long id) {
        log.debug("Request to get Command : {}", id);
        Command command = commandRepository.findOne(id);
        CommandDTO commandDTO = commandMapper.commandToCommandDTO(command);
        return commandDTO;
    }

    /**
     *  Delete the  command by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Command : {}", id);
        commandRepository.delete(id);
    }
}
