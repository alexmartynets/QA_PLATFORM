package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.UserBadgesDto;
import com.javamentor.qa.platform.models.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserDtoDAO {

    List<UserDto> getUserDtoList();

    Optional<UserDto> getUserDtoById(Long id);

    Long getCountUsers();

    List<UserDto> getListUsersForPagination(int page, int count);

    //    methods for statistics
    List<UserBadgesDto> getUserBadges(Long userId, Integer page);

    Long getCountOfUserBadges(Long userId);

    Long getAllViews(Long userId);

}
