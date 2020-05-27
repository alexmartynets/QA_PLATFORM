package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.UserDto;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public interface UserDtoDAO {

    List<UserDto> getUserDtoList();

    Optional<UserDto> getUserDtoById(Long id);

    Long getCountUsers();

    Long getCountUsersByName(String name);

    List<UserDto> getListUsersToPagination(int page, int count);

    List<UserDto> getListUsersByNameToSearch(String name, int page, int count);
}
