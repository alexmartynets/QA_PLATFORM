package com.javamentor.qa.platform.service.statistics.abstracts;

import com.javamentor.qa.platform.models.dto.UserStatisticDto;

public interface Tab {
    UserStatisticDto getStatistics(Long user_id, String sort, int page);
}