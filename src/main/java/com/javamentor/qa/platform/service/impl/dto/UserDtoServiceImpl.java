package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDAO;
import com.javamentor.qa.platform.models.dto.EditorDto;
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

    public UserDtoServiceImpl(UserDtoDAO userDtoDao) {
        this.userDtoDao = userDtoDao;
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
    public Pair<List<ReputationDto>, Long> getListNewUsersByReputation(int page, int count, long weeks){
        List<ReputationDto> listUsersDto = userDtoDao.getListNewUsersByReputation(page, count, weeks);
        Long countUsers = userDtoDao.getCountNewUsersByReputation(weeks);
        return new Pair<>(listUsersDto, countUsers);
    };

    @Override
    public Pair<List<ReputationDto>, Long> getListUsersByCreationDate(int page, int count, long weeks) {
        List<ReputationDto> listUsersDto = userDtoDao.getListUsersByCreationDate(page, count, weeks);
        Long countUsers = userDtoDao.getCountUsersByCreationDate(weeks);
        return new Pair<>(listUsersDto, countUsers);
    }

    @Override
    public Pair<List<ReputationDto>, Long> getListUsersByReputation(int page, int count, long weeks) {
        List<ReputationDto> reputationDtoList = userDtoDao.getListUsersByReputation(page, count, weeks);
        Long counts = userDtoDao.getCountUsersByReputation(weeks);
        return new Pair<>(reputationDtoList, counts);
    }

    @Override
    public Pair<List<ReputationDto>, Long> getListUsersByVoice(int page, int count, long weeks) {
        List<ReputationDto> reputationDtoList = userDtoDao.getListUsersByVoice(page, count, weeks);
        Long counts = userDtoDao.getCountUsersByVoice(weeks);
        return new Pair<>(reputationDtoList, counts);
    }

    @Override
    public Pair<List<ReputationDto>, Long> getListUsersByNameToSearch(String name, int page, int count, long weeks) {
        List<ReputationDto> reputationDtoList = userDtoDao.getListUsersByNameToSearch(name, page, count, weeks);
        Long counts = userDtoDao.getCountUsersByName(name, weeks);
        return new Pair<>(reputationDtoList, counts);
    }

    @Override
    public Pair<List<EditorDto>, Long> getListUsersByQuantityEditedText(int page, int count, long weeks) {
        List<EditorDto> editorDtoList = userDtoDao.getListUsersByQuantityEditedText(page, count, weeks);
        Long counts = userDtoDao.getCountUsersByQuantityEditedText(weeks);
        return new Pair<>(editorDtoList, counts);
    }

    @Override
    public Pair<List<UserDto>, Long> getListUsersByRole(String role) {
        List<UserDto> listUsersDto = userDtoDao.getListUsersByRole(role);
        long countUsers = listUsersDto.size();
        return new Pair<>(listUsersDto, countUsers);
    }
}
