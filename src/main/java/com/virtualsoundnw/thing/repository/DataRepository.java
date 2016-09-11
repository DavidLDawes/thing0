package com.virtualsoundnw.thing.repository;

import com.virtualsoundnw.thing.domain.Data;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Data entity.
 */
@SuppressWarnings("unused")
public interface DataRepository extends JpaRepository<Data,Long> {

}
