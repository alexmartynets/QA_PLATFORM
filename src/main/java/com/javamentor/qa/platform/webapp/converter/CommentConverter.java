package com.javamentor.qa.platform.webapp.converter;

import com.javamentor.qa.platform.models.dto.CommentDto;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CommentConverter {

//    @Mapping(source = "commentDto.id", target = "id")
//    @Mapping(source = "commentDto.text", target = "text")
//    @Mapping(source = "commentDto.commentType", target = "commentType")
//    @Mapping(source = "commentDto.persistDateTime", target = "persistDateTime")
//    @Mapping(source = "commentDto.lastUpdateDateTime", target = "lastUpdateDateTime")
//    @Mapping(source = "userId", target = "user.id")
//    public abstract Comment toComment(CommentDto commentDto, User user);

}

/*
    @Mapper
    interface DeliveryAddressMapper {

    @Mapping(source = "customer.firstName", target = "forename")
    @Mapping(source = "customer.lastName", target = "surname")
    @Mapping(source = "address.street", target = "street")
    @Mapping(source = "address.postalcode", target = "postalcode")
    @Mapping(source = "address.county", target = "county")
    DeliveryAddress from(Customer customer, Address address);

}*/

/*Hibernate:
    insert
    into
        comment
        (comment_type, last_redaction_date, persist_date, text, user_id)
    values
        (?, ?, ?, ?, ?)*/

