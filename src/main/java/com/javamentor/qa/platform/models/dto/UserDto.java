package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.entity.User;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


@Getter
@Setter
@NoArgsConstructor
@Data
public class UserDto {

    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значение при обновлении")
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String role;

    public static class Builder {
        private final UserDto userDto;

        public Builder() {
            userDto = new UserDto();
        }

        public Builder withId(Long id){
            userDto.id = id;
            return this;
        }

        public Builder withEmail(String email){
            userDto.email = email;
            return this;
        }

        public Builder withPassword(String password){
            userDto.password = password;
            return this;
        }

        public Builder withRole(String role){
            userDto.role = role;
            return this;
        }


        public UserDto build(){
            return userDto;
        }

    }

}
