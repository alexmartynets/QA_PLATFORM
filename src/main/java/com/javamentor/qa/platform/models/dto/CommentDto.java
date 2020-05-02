package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.util.action.OnCreate;
import com.javamentor.qa.platform.models.util.action.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    @Null(groups = OnCreate.class, message = "Должно принимать null значение при создании")
    @NotNull(groups = OnUpdate.class, message = "Не должно принимать null значние при обновлении")
    private Long id;

    @NotNull
    private String text;

    @NotNull
    private CommentType commentType;

//    public static class Builder {
//        private final CommentDto commentDto;
//
//        public Builder() {
//            commentDto = new CommentDto();
//        }
//
//        public CommentDto.Builder withId(Long id){
//            commentDto.id = id;
//            return this;
//        }
//
//        public CommentDto.Builder withText(String text){
//            commentDto.text = text;
//            return this;
//        }
//
//        public CommentDto.Builder withCommentType(CommentType commentType){
//            commentDto.commentType = commentType;
//            return this;
//        }
//
//        public CommentDto build(){
//            return commentDto;
//        }
//
//    }
}
