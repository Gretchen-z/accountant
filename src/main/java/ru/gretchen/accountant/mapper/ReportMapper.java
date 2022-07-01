package ru.gretchen.accountant.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.gretchen.accountant.model.Report;
import ru.gretchen.accountant.model.dto.ReportDTO;

@Mapper
@DecoratedWith(ReportMapperDecorator.class)
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Mapping(target = "date", source = "date")
    ReportDTO fromEntity(Report entity);
}
