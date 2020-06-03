package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class UserDto {

    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    @ApiModelProperty(notes = "Автоматически генерируемый ID пользователя. Не указывать при создании, обязательно указывать при изменении учетной записи")
    private Long id;

    @NotNull (groups = OnCreate.class, message = "Поле не должно быть пустым")
    @ApiModelProperty(notes = "Полное имя пользователя, поле не должно быть пустым", required = true, example = "Иванов Иван")
    private String fullName;

    @NotNull
    @ApiModelProperty(notes = "Email должен быть корректным, смотрите пример", required = true, example = "email@email.com")
    @Email(regexp=".@.\\..*", message = "Email должен быть корректным")
    private String email;

    @NotNull
    @ApiModelProperty(notes = "Должен содержать минимум 8 символов, 1 заглавную букву и 1 цифру", required = true, example = "Qwerty12")
    private String password;

    @NotNull(groups = OnCreate.class, message = "Автоматически назначается при создании всем пользователям, явно указывать не нужно")
    @ApiModelProperty(notes = "Автоматически назначается при создании всем пользователям, явно указывать не нужно", example = "не указывать")
    private String role;

    @ApiModelProperty(notes = "Картинка пользователя, на вход принимает массив байт", example = "byte[]")
    private byte[] imageUser;

    @ApiModelProperty(notes = "Любая информация о пользователе (не является обязательным полем)", example = "About me")
    private String about;

    @ApiModelProperty(notes = "Город пользователя (не является обязательным полем)", example = "Moscow")
    private String city;

    @ApiModelProperty(notes = "Ссылка на сайт пользователя", example = "www.user.com")
    private String linkSite;

    @ApiModelProperty(notes = "Ссылка на репозитории GitHub пользователя", example = "www.github.com/user")
    private String linkGitHub;

    @ApiModelProperty(notes = "Ссылка на страницу VK пользователя", example = "www.vk.com/id12345678")
    private String linkVk;

    @ApiModelProperty(notes = "Репутация пользователя, по умолчанию 0", example = "не указывать")
    private Integer reputationCount;

    @ApiModelProperty(notes = "Дата создания учетной записи пользователя, явно указывать не нужно, назначается автоматически при создании", example = "не указывать")
    private LocalDateTime persistDateTime;

    @ApiModelProperty(notes = "Дата изменения учетной записи пользователя, явно указывать не нужно, назначается автоматически при внесении изменений", example = "не указывать")
    private LocalDateTime lastUpdateDateTime;
}
