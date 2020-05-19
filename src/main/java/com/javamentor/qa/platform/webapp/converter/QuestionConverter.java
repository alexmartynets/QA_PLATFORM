package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.QuestionDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class QuestionConverter {

    public abstract Question toEntity(QuestionDto questionDto);
    public abstract QuestionDto toDto(Question question);
    public abstract List<Question> toEntityList(List<QuestionDto> entityList);
    public abstract List<QuestionDto> toDtoList(List<Question> dtoList);

}
