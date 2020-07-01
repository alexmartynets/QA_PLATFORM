package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class UserDto implements Serializable {

    @ApiModelProperty(notes = "Автоматически генерируемый ID пользователя. Не указывать при создании, " +
            "обязательно указывать при изменении учетной записи", position = 1)
    @Null(groups = OnCreate.class, message = "Поле id должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Поле id не должно принимать null значение при обновлении")
    private Long id;

    @ApiModelProperty(notes = "Полное имя пользователя, поле не должно быть пустым",
            required = true, example = "Иванов Иван", position = 4)
    @NotNull(groups = OnCreate.class, message = "Поле имя не должно быть Null при создании")
    @Pattern(groups = OnCreate.class, regexp = "[а-яА-ЯёЁa-zA-Z]+.*$", message = "Поле имя должен начинаться с буквы")
    private String fullName;

    @ApiModelProperty(notes = "Email должен быть корректным, смотрите пример",
            required = true, example = "email@email.com", position = 2)
    @NotNull(groups = OnCreate.class, message = "Поле Email не должно быть Null при создании")
    @Email(regexp = "^[a-zA-Z0-9]{1,}" + "((\\.|\\_|-{0,})[a-zA-Z0-9]{1,})*" + "@" + "[a-zA-Z0-9]{1,}" +
            "((\\.|\\_|-{0,1})[a-zA-Z0-9]{1,})*" + "\\.[a-zA-Z]{2,}$",
            message = "Email должен быть корректным")
    private String email;

    @ApiModelProperty(notes = "Должен содержать минимум 8 символов, 1 заглавную букву и 1 цифру",
            required = true, example = "Qwerty12", position = 3)
    @NotNull(groups = OnCreate.class, message = "Поле password не должно быть Null при создании")
    @Pattern(groups = OnCreate.class, regexp = "^(?=.*\\d)(?=.*[A-Z])[a-zA-Z0-9]+$",
            message = "Поле password должен содержать 1 цифру, 1 заглавную букву.")
    @Size(groups = OnCreate.class, min = 8, message = "Поле password должен быть не мение 8 символов.")
    private String password;

    @ApiModelProperty(notes = "Автоматически назначается при создании всем пользователям, явно указывать не нужно",
            example = "не указывать", hidden = true)
    @Null(groups = OnCreate.class, message = " 'role' автоматически назначается при создании всем пользователям, " +
            "явно указывать не нужно")
    private String role;

    @ApiModelProperty(notes = "Картинка пользователя, на вход принимает массив байт", example = "byte[]", position = 10)
    private byte[] imageUser;

    @ApiModelProperty(notes = "Любая информация о пользователе (не является обязательным полем)",
            example = "About me", position = 6)
    private String about;

    @ApiModelProperty(notes = "Город пользователя (не является обязательным полем)", example = "Moscow", position = 5)
    private String city;

    @ApiModelProperty(notes = "Ссылка на сайт пользователя", example = "www.user.com", position = 7)
    private String linkSite;

    @ApiModelProperty(notes = "Ссылка на репозитории GitHub пользователя", example = "www.github.com/user", position = 8)
    private String linkGitHub;

    @ApiModelProperty(notes = "Ссылка на страницу VK пользователя", example = "www.vk.com/id12345678", position = 9)
    private String linkVk;

    @ApiModelProperty(notes = "Репутация пользователя, по умолчанию 0", example = "не указывать", hidden = true)
    private Integer reputationCount = 0;

    @ApiModelProperty(notes = "Количестро правок внесенных в сообщения других участников",
            example = "не указывать", hidden = true)
    private Integer countChanges;

    @ApiModelProperty(notes = "Количество голосов отданных другими участникими за ваши вопросы, ответы и правки.",
            example = "не указывать", hidden = true)
    private Integer countVoice;

    @ApiModelProperty(notes = "Дата создания учетной записи Модератор", example = "не указывать", hidden = true)
    private LocalDateTime dateAssignmentModerator;

    @ApiModelProperty(notes = "Дата создания учетной записи пользователя, "
            + "явно указывать не нужно, назначается автоматически при создании", example = "не указывать", hidden = true)
    private LocalDateTime persistDateTime;

    @ApiModelProperty(notes = "Дата изменения учетной записи пользователя, явно указывать не нужно, " +
            "назначается автоматически при внесении изменений", example = "не указывать", hidden = true)
    private LocalDateTime lastUpdateDateTime;

}