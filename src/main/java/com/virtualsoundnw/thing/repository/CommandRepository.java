package com.virtualsoundnw.thing.repository;

import com.virtualsoundnw.thing.domain.Command;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Command entity.
 */
@SuppressWarnings("unused")
public interface CommandRepository extends JpaRepository<Command,Long> {

}
