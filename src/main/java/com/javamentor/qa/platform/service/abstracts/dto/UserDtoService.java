package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.UserDto;

import java.util.List;

public interface UserDtoService {
    List<UserDto> getUserDtoList();
    UserDto getUserDtoById(Long id);
}