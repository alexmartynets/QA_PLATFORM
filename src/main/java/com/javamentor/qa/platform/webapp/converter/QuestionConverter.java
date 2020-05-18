package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class QuestionConverter {
//    @Mappings({
//            @Mapping(target = "user.id", source = "userDto.id"),})
    public abstract Question toEntity(QuestionDto questionDto);
//    @Mappings({
//            @Mapping(target = "userDto.di", source = "user.id")
//    })
    public abstract QuestionDto toDto(Question question);
    public abstract List<Question> toEntityList(List<QuestionDto> entityList);
    public abstract List<QuestionDto> toDtoList(List<Question> dtoList);

}
