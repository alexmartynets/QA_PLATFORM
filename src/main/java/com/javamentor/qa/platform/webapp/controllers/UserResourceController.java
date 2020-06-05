package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.EditorDto;
import com.javamentor.qa.platform.models.dto.ReputationDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.models.entity.Editor;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.webapp.converter.UserConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.util.Pair;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
@Api(value = "UserApi", description = "Операции с пользователем (создание, изменение, получение списка, получение пользователя по ID)")
public class UserResourceController {

    private final UserService userService;
    private final UserDtoService userDtoService;
    private final UserConverter userConverter;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserResourceController(UserService userService, UserDtoService userDtoService, UserConverter userConverter) {
        this.userService = userService;
        this.userDtoService = userDtoService;
        this.userConverter = userConverter;
    }

    @ApiOperation(value = "Добавление пользователя")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь добавлен")
    })
    public ResponseEntity<UserDto> addUser(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        userService.persist(userConverter.toEntity(userDto));
        logger.info(String.format("Пользователь с email: %s добавлен в базу данных", userDto.getEmail()));
        return ResponseEntity.ok().body(userDto);
    }

    @ApiOperation(value = "получение списка доступных пользователей")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список пользователей получен")
    })
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return ResponseEntity.ok(userDtoService.getUserDtoList());
    }

    @ApiOperation(value = "Изменение пользователя (параметр ID обязателен)")
    @PutMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Данные пользователя обновлены")
    })
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = userConverter.toEntity(userDto);
        user.setId(id);
        userService.update(user);
        logger.info(String.format("user с ID: %d updated successfully", userDto.getId()));
        return ResponseEntity.ok().body(userConverter.toDto(user));
    }

    @ApiOperation(value = "Поиск пользователя по ID")
    @GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь найден по id"),
            @ApiResponse(code = 404, message = "Пользователь не найден по id")
    })
    public ResponseEntity<UserDto> findUser(@PathVariable Long id) {
        Optional<UserDto> optionalUserDto = userDtoService.getUserDtoById(id);
        if (optionalUserDto.isPresent()) {
            UserDto userDto = optionalUserDto.get();
            return ResponseEntity.ok(userDto);
        } else {
            logger.error(String.format("Пользователь с указанным ID: %d не найден!", id));
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "получение списка новых пользователей c пагинацией")
    @GetMapping(path = "/new")  // ?count=20&page=1&weeks=2
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список пользователей получен")
    })
    public ResponseEntity<Pair<List<UserDto>, Long>> getListNewUsers(@RequestParam @NonNull Long count,
                                                                     @RequestParam @NonNull Long page,
                                                                     @RequestParam @NonNull Long weeks) {
        return ResponseEntity.ok().body(userDtoService
                .getListNewUsers(page.intValue(), count.intValue(), weeks));
    }

    @ApiOperation(value = "получение списка пользователей по репутации c пагинацией")
    @GetMapping(path = "/reputation") // ?count=20&page=1&weeks=12
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список пользователей получен")
    })
    public ResponseEntity<Pair<List<ReputationDto>, Long>> getListUsersByReputation(@PathVariable @NonNull Long count,
                                                                                    @PathVariable @NonNull Long page,
                                                                                    @PathVariable @NonNull Long weeks) {
        return ResponseEntity.ok().body(userDtoService
                .getListUsersByReputation(page.intValue(), count.intValue(), weeks));
    }

    @ApiOperation(value = "получение списка пользователей по голосам с пагинацией")
    @GetMapping(path = "/voice") // ?count=20&page=1&weeks=12
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список пользователей получен")
    })
    public ResponseEntity<Pair<List<ReputationDto>, Long>> getListUsersByVoice(@PathVariable @NonNull Long count,
                                                                               @PathVariable @NonNull Long page,
                                                                               @PathVariable @NonNull Long weeks) {
        return ResponseEntity.ok().body(userDtoService
                .getListUsersByVoice(page.intValue(), count.intValue(), weeks));
    }

    @ApiOperation(value = "получение списка редакторов с пагинацией")
    @GetMapping(path = "/editor") // ?count=20&page=1&weeks=12
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список редакторов получен")
    })
    public ResponseEntity<Pair<List<EditorDto>, Long>> getListUsersByQuantityEditedText(@PathVariable @NonNull Long count,
                                                                                        @PathVariable @NonNull Long page,
                                                                                        @PathVariable @NonNull Long weeks) {
        return ResponseEntity.ok().body(userDtoService
                .getListUsersByQuantityEditedText(page.intValue(), count.intValue(), weeks));
    }

    @ApiOperation(value = "получение списка пользователей для поиска по имяни с погинацией")
    @GetMapping(path = "/search") // ?name=Андрей&count=20&page=1&weeks=12
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список пользователей получен")
    })
    public ResponseEntity<Pair<List<ReputationDto>, Long>> getListUsersByNameToSearch(@RequestParam @NonNull String name,
                                                                                      @PathVariable @NonNull Long count,
                                                                                      @PathVariable @NonNull Long page,
                                                                                      @PathVariable @NonNull Long weeks) {
        return ResponseEntity.ok().body(userDtoService
                .getListUsersByNameToSearch(name, page.intValue(), count.intValue(), weeks));
    }
}
