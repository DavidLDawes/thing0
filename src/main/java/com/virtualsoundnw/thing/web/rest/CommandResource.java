package com.virtualsoundnw.thing.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.virtualsoundnw.thing.service.CommandService;
import com.virtualsoundnw.thing.web.rest.util.HeaderUtil;
import com.virtualsoundnw.thing.service.dto.CommandDTO;
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
 * REST controller for managing Command.
 */
@RestController
@RequestMapping("/api")
public class CommandResource {

    private final Logger log = LoggerFactory.getLogger(CommandResource.class);
        
    @Inject
    private CommandService commandService;

    /**
     * POST  /commands : Create a new command.
     *
     * @param commandDTO the commandDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new commandDTO, or with status 400 (Bad Request) if the command has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/commands",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CommandDTO> createCommand(@Valid @RequestBody CommandDTO commandDTO) throws URISyntaxException {
        log.debug("REST request to save Command : {}", commandDTO);
        if (commandDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("command", "idexists", "A new command cannot already have an ID")).body(null);
        }
        CommandDTO result = commandService.save(commandDTO);
        return ResponseEntity.created(new URI("/api/commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("command", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /commands : Updates an existing command.
     *
     * @param commandDTO the commandDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated commandDTO,
     * or with status 400 (Bad Request) if the commandDTO is not valid,
     * or with status 500 (Internal Server Error) if the commandDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/commands",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CommandDTO> updateCommand(@Valid @RequestBody CommandDTO commandDTO) throws URISyntaxException {
        log.debug("REST request to update Command : {}", commandDTO);
        if (commandDTO.getId() == null) {
            return createCommand(commandDTO);
        }
        CommandDTO result = commandService.save(commandDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("command", commandDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /commands : get all the commands.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of commands in body
     */
    @RequestMapping(value = "/commands",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CommandDTO> getAllCommands() {
        log.debug("REST request to get all Commands");
        return commandService.findAll();
    }

    /**
     * GET  /commands/:id : get the "id" command.
     *
     * @param id the id of the commandDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the commandDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/commands/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CommandDTO> getCommand(@PathVariable Long id) {
        log.debug("REST request to get Command : {}", id);
        CommandDTO commandDTO = commandService.findOne(id);
        return Optional.ofNullable(commandDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /commands/:id : delete the "id" command.
     *
     * @param id the id of the commandDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/commands/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCommand(@PathVariable Long id) {
        log.debug("REST request to delete Command : {}", id);
        commandService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("command", id.toString())).build();
    }

}
