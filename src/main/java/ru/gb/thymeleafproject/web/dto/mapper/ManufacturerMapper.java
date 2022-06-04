package ru.gb.thymeleafproject.web.dto.mapper;

import org.mapstruct.Mapper;
import ru.gb.gbapimay.manufacturer.dto.ManufacturerDto;
import ru.gb.thymeleafproject.entity.Manufacturer;

@Mapper
public interface ManufacturerMapper {
    Manufacturer toManufacturer(ManufacturerDto manufacturerDto);

    ManufacturerDto toManufacturerDto(Manufacturer manufacturer);
}
