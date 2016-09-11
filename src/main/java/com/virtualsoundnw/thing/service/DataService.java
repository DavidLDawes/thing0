package com.virtualsoundnw.thing.service;

import com.virtualsoundnw.thing.service.dto.DataDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Data.
 */
public interface DataService {

    /**
     * Save a data.
     *
     * @param dataDTO the entity to save
     * @return the persisted entity
     */
    DataDTO save(DataDTO dataDTO);

    /**
     *  Get all the data.
     *  
     *  @return the list of entities
     */
    List<DataDTO> findAll();

    /**
     *  Get the "id" data.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DataDTO findOne(Long id);

    /**
     *  Delete the "id" data.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
