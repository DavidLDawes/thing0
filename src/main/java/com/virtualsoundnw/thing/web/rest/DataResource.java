package com.virtualsoundnw.thing.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.virtualsoundnw.thing.service.DataService;
import com.virtualsoundnw.thing.web.rest.util.HeaderUtil;
import com.virtualsoundnw.thing.service.dto.DataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Data.
 */
@RestController
@RequestMapping("/api")
public class DataResource {

    private final Logger log = LoggerFactory.getLogger(DataResource.class);
        
    @Inject
    private DataService dataService;

    /**
     * POST  /data : Create a new data.
     *
     * @param dataDTO the dataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dataDTO, or with status 400 (Bad Request) if the data has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/data",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DataDTO> createData(@Valid @RequestBody DataDTO dataDTO) throws URISyntaxException {
        log.debug("REST request to save Data : {}", dataDTO);
        if (dataDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("data", "idexists", "A new data cannot already have an ID")).body(null);
        }
        DataDTO result = dataService.save(dataDTO);
        return ResponseEntity.created(new URI("/api/data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("data", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /data : Updates an existing data.
     *
     * @param dataDTO the dataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dataDTO,
     * or with status 400 (Bad Request) if the dataDTO is not valid,
     * or with status 500 (Internal Server Error) if the dataDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/data",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DataDTO> updateData(@Valid @RequestBody DataDTO dataDTO) throws URISyntaxException {
        log.debug("REST request to update Data : {}", dataDTO);
        if (dataDTO.getId() == null) {
            return createData(dataDTO);
        }
        DataDTO result = dataService.save(dataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("data", dataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /data : get all the data.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of data in body
     */
    @RequestMapping(value = "/data",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<DataDTO> getAllData() {
        log.debug("REST request to get all Data");
        return dataService.findAll();
    }

    /**
     * GET  /data/:id : get the "id" data.
     *
     * @param id the id of the dataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dataDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/data/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DataDTO> getData(@PathVariable Long id) {
        log.debug("REST request to get Data : {}", id);
        DataDTO dataDTO = dataService.findOne(id);
        return Optional.ofNullable(dataDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /data/:id : delete the "id" data.
     *
     * @param id the id of the dataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/data/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        log.debug("REST request to delete Data : {}", id);
        dataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("data", id.toString())).build();
    }

}
