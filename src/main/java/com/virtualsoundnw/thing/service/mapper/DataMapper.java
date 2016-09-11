package com.virtualsoundnw.thing.service.mapper;

import com.virtualsoundnw.thing.domain.*;
import com.virtualsoundnw.thing.service.dto.DataDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Data and its DTO DataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DataMapper {

    DataDTO dataToDataDTO(Data data);

    List<DataDTO> dataToDataDTOs(List<Data> data);

    Data dataDTOToData(DataDTO dataDTO);

    List<Data> dataDTOsToData(List<DataDTO> dataDTOs);
}
