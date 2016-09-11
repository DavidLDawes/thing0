package com.virtualsoundnw.thing.service.impl;

import com.virtualsoundnw.thing.service.DataService;
import com.virtualsoundnw.thing.domain.Data;
import com.virtualsoundnw.thing.repository.DataRepository;
import com.virtualsoundnw.thing.service.dto.DataDTO;
import com.virtualsoundnw.thing.service.mapper.DataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Data.
 */
@Service
@Transactional
public class DataServiceImpl implements DataService{

    private final Logger log = LoggerFactory.getLogger(DataServiceImpl.class);
    
    @Inject
    private DataRepository dataRepository;

    @Inject
    private DataMapper dataMapper;

    /**
     * Save a data.
     *
     * @param dataDTO the entity to save
     * @return the persisted entity
     */
    public DataDTO save(DataDTO dataDTO) {
        log.debug("Request to save Data : {}", dataDTO);
        Data data = dataMapper.dataDTOToData(dataDTO);
        data = dataRepository.save(data);
        DataDTO result = dataMapper.dataToDataDTO(data);
        return result;
    }

    /**
     *  Get all the data.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<DataDTO> findAll() {
        log.debug("Request to get all Data");
        List<DataDTO> result = dataRepository.findAll().stream()
            .map(dataMapper::dataToDataDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one data by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public DataDTO findOne(Long id) {
        log.debug("Request to get Data : {}", id);
        Data data = dataRepository.findOne(id);
        DataDTO dataDTO = dataMapper.dataToDataDTO(data);
        return dataDTO;
    }

    /**
     *  Delete the  data by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Data : {}", id);
        dataRepository.delete(id);
    }
}
