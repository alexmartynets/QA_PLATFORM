package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.UserDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestControllerAdvice
@RestController
@RequestMapping("/api/user")
@ApiResponses(value = {
        @ApiResponse(code = 404, message = "Ресурс не найден, проверьте правильность пути")
})
@Api(value="UserApi", description = "Операции с пользователем (создание, изменение, получение списка, получение пользователя по ID)")
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
    @PostMapping(produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь добавлен"),
            @ApiResponse(code = 401, message = "Вы не авторизованы, пожалуйста авторизуйтесь"),
            @ApiResponse(code = 403, message = "Доступ к ресурсу запрещен, недостаточно прав для доступа")
    })
    public ResponseEntity<UserDto> addUser(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        userService.persist(userConverter.toEntity(userDto));
        logger.info(userDto.toString() + " saved into database successfully");
        return ResponseEntity.ok().body(userDto);
    }

    @ApiOperation(value = "получение списка доступных пользователей")
    @GetMapping(produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список пользователей получен"),
    })
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return ResponseEntity.ok(userDtoService.getUserDtoList());
    }

    @ApiOperation(value = "Изменение пользователя (параметр ID обязателен)")
    @PutMapping(path = "/{id}", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Данные пользователя обновлены"),
            @ApiResponse(code = 401, message = "Вы не авторизованы, пожалуйста авторизуйтесь"),
            @ApiResponse(code = 403, message = "Доступ к ресурсу запрещен, недостаточно прав для доступа")
    })
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = userConverter.toEntity(userDto);
        user.setId(id);
        userService.update(user);
        logger.info("user updated successfully");
        return ResponseEntity.ok().body(userConverter.toDto(user));
    }

    @ApiOperation(value = "Поиск пользователя по ID")
    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь найден по id"),
            @ApiResponse(code = 401, message = "Вы не авторизованы, пожалуйста авторизуйтесь"),
            @ApiResponse(code = 403, message = "Доступ к ресурсу запрещен, недостаточно прав для доступа")
    })
    public ResponseEntity<UserDto> findUser(@PathVariable Long id) {
        Optional<UserDto> optionalUserDto = userDtoService.getUserDtoById(id);
        if (optionalUserDto.isPresent()) {
            UserDto userDto = optionalUserDto.get();
            return ResponseEntity.ok(userDto);
        } else {
            logger.error("Пользователь с указанным ID: " + id + " не найден!");
            return ResponseEntity.notFound().build();
        }
    }
}