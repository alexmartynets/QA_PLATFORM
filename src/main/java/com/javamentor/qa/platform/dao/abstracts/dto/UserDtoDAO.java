package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.EditorDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public interface UserDtoDAO {

    List<UserDto> getUserDtoList();

    Optional<UserDto> getUserDtoById(Long id);

    Long getCountNewUsers(long weeks);

    Long getCountUsersByQuantityEditedText(long weeks);

    List<UserDto> getListNewUsers(int page, int count, long weeks);

    List<EditorDto> getListUsersByQuantityEditedText(int page, int count, long weeks);
}
