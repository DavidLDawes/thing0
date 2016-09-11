package com.virtualsoundnw.thing.service;

import com.virtualsoundnw.thing.service.dto.CommandDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Command.
 */
public interface CommandService {

    /**
     * Save a command.
     *
     * @param commandDTO the entity to save
     * @return the persisted entity
     */
    CommandDTO save(CommandDTO commandDTO);

    /**
     *  Get all the commands.
     *  
     *  @return the list of entities
     */
    List<CommandDTO> findAll();

    /**
     *  Get the "id" command.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CommandDTO findOne(Long id);

    /**
     *  Delete the "id" command.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
