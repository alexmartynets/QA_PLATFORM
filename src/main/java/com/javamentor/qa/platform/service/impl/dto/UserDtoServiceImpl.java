package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.ReputationDtoDAO;
import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDAO;
import com.javamentor.qa.platform.models.dto.ReputationDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDtoServiceImpl implements UserDtoService {

    private final UserDtoDAO userDtoDao;

    private final ReputationDtoDAO reputationDtoDAO;

    public UserDtoServiceImpl(UserDtoDAO userDtoDao, ReputationDtoDAO reputationDtoDAO) {
        this.userDtoDao = userDtoDao;
        this.reputationDtoDAO = reputationDtoDAO;
    }

    @Override
    public List<UserDto> getUserDtoList() {
        return userDtoDao.getUserDtoList();
    }

    @Override
    public Optional<UserDto> getUserDtoById(Long id) {
        return userDtoDao.getUserDtoById(id);
    }

    @Override
    public Pair<List<UserDto>, Long> getListNewUsersToPagination(int page, int count, long weeks) {
        List<UserDto> listUsersDto = userDtoDao.getListNewUsersToPagination(page, count, weeks);
        Long countUsers = userDtoDao.getCountNewUsers(weeks);
        return new Pair<>(listUsersDto, countUsers);
    }

    @Override
    public Pair<List<ReputationDto>, Long> getListUsersByReputationToPagination(int page, int count, long weeks) {
        List<ReputationDto> reputationDtoList = reputationDtoDAO.getListUsersByReputationToPagination(page, count, weeks);
        Long counts = reputationDtoDAO.getCountUsers(weeks);
        return new Pair<>(reputationDtoList, counts);
    }

    @Override
    public Pair<List<ReputationDto>, Long> getListUsersByVoiceToPagination(int page, int count, long weeks) {
        List<ReputationDto> reputationDtoList = reputationDtoDAO.getListUsersByVoiceToPagination(page, count, weeks);
        Long counts = reputationDtoDAO.getCountUsers(weeks);
        return new Pair<>(reputationDtoList, counts);
    }

    @Override
    public Pair<List<ReputationDto>, Long> getListUsersByNameToSearch(String name, int page, int count, long weeks) {
        List<ReputationDto> reputationDtoList = reputationDtoDAO.getListUsersByNameToSearch(name, page, count, weeks);
        Long counts = reputationDtoDAO.getCountUsersByName(name, weeks);
        return new Pair<>(reputationDtoList, counts);
    }
}
