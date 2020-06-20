package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.UserDto;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public interface UserDtoService {

    List<UserDto> getUserDtoList();

    Optional<UserDto> getUserDtoById(Long id);

    Pair<List<UserDto>, Long> getListNewUsersByReputation(int page, int count, long weeks);

    Pair<List<UserDto>, Long> getListUsersByCreationDate(int page, int count, long weeks);

    Pair<List<UserDto>, Long> getListUsersByReputation(int page, int count, long weeks);

    Pair<List<UserDto>, Long> getListUsersByVoice(int page, int count, long weeks);

    Pair<List<UserDto>, Long> getListUsersByNameToSearch(String name, int page, int count, long weeks);

    Pair<List<UserDto>, Long> getListUsersByQuantityEditedText(int page, int count, long weeks);

    Pair<List<UserDto>, Long> getListUsersByModerator();

    boolean isNumbersGreaterZero(Long count, Long page, Long weeks);

    boolean isString (String name);
}