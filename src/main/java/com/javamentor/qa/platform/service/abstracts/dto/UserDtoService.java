package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.EditorDto;
import com.javamentor.qa.platform.models.dto.ReputationDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public interface UserDtoService {

    List<UserDto> getUserDtoList();

    Optional<UserDto> getUserDtoById(Long id);

    Pair<List<UserDto>, Long> getListNewUsers(int page, int count, long weeks);

    Pair<List<ReputationDto>, Long> getListUsersByReputation(int page, int count, long weeks);

    Pair<List<ReputationDto>, Long> getListUsersByVoice(int page, int count, long weeks);

    Pair<List<ReputationDto>, Long> getListUsersByNameToSearch(String name, int page, int count, long weeks);

    Pair<List<EditorDto>, Long> getListUsersByQuantityEditedText(int page, int count, long weeks);
}