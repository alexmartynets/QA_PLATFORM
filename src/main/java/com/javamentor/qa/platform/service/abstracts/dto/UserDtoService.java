package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.ReputationDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public interface UserDtoService {

    List<UserDto> getUserDtoList();

    Optional<UserDto> getUserDtoById(Long id);

    Pair<List<UserDto>, Long> getListNewUsersToPagination(int page, int count, long weeks);

    Pair<List<ReputationDto>, Long> getListUsersByReputationToPagination(int page, int count, long weeks);

    Pair<List<ReputationDto>, Long> getListUsersByVoiceToPagination(int page, int count, long weeks);

    Pair<List<ReputationDto>, Long> getListUsersByNameToSearch(String name, int page, int count, long weeks);
}