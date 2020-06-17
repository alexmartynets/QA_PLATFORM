package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.UserBadgesDto;

import java.util.List;

public interface UserBadgesDtoDAO {
    List<UserBadgesDto> getUserBadgesDto(Long user_id);
}
