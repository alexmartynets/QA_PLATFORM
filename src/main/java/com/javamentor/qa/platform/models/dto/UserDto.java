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
    @ApiModelProperty(notes = "Полное имя пользователя, поле не должно быть пустым")
    private String fullName;

    @NotNull
    @ApiModelProperty(notes = "Email должен быть корректным, смотрите пример")
    @Email(regexp=".@.\\..*", message = "Email должен быть корректным, смотрите пример")
    private String email;

    @NotNull
    @ApiModelProperty(notes = "Должен содержать минимум 8 символов, 1 заглавную букву и 1 цифру")
    private String password;

    @NotNull(groups = OnCreate.class, message = "Автоматически назначается при создании всем пользователям, явно указывать не нужно")
    @ApiModelProperty(notes = "Автоматически назначается при создании всем пользователям, явно указывать не нужно")
    private String role;

    @ApiModelProperty(notes = "Картинка пользователя, на вход принимает массив байт")
    private byte[] imageUser;

    @ApiModelProperty(notes = "Любая информация о пользователе (не является обязательным полем)")
    private String about;

    @ApiModelProperty(notes = "Город пользователя (не является обязательным полем)")
    private String city;

    @ApiModelProperty(notes = "Ссылка на сайт пользователя")
    private String linkSite;

    @ApiModelProperty(notes = "Ссылка на репозитории GitHub пользователя")
    private String linkGitHub;

    @ApiModelProperty(notes = "Ссылка на страницу VK пользователя")
    private String linkVk;

    @ApiModelProperty(notes = "Репутация пользователя, по умолчанию 0")
    private Integer reputationCount;

    @ApiModelProperty(notes = "Дата создания учетной записи пользователя, явно указывать не нужно, назначается автоматически при создании")
    private LocalDateTime persistDateTime;

    @ApiModelProperty(notes = "Дата изменения учетной записи пользователя, явно указывать не нужно, назначается автоматически при внесении изменений")
    private LocalDateTime lastUpdateDateTime;
}
