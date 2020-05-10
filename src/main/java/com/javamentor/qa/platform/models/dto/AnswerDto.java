package com.javamentor.qa.platform.models.dto;

import java.sql.Blob;
import lombok.*;
import java.time.LocalDateTime;


@Builder
@EqualsAndHashCode
@ToString
public class AnswerDto {
    private Long id;
    private String htmlBody;
    private LocalDateTime persistDateTime;
    private Integer countValuable;
    private Boolean isHelpful;
    private String fullName;
    private Blob imageUser;
    private Integer reputationCount;
    private Long questionId;
    private Long userId;

    public AnswerDto() {
    }

    public AnswerDto(Long id, String htmlBody, LocalDateTime persistDateTime, Integer countValuable, Boolean isHelpful, String fullName, Blob imageUser, Integer reputationCount, Long questionId, Long userId) {
        this.id = id;
        this.htmlBody = htmlBody;
        this.persistDateTime = persistDateTime;
        this.countValuable = countValuable;
        this.isHelpful = isHelpful;
        this.fullName = fullName;
        this.imageUser = imageUser;
        this.reputationCount = reputationCount;
        this.questionId = questionId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public LocalDateTime getPersistDateTime() {
        return persistDateTime;
    }

    public void setPersistDateTime(LocalDateTime persistDateTime) {
        this.persistDateTime = persistDateTime;
    }

    public Integer getCountValuable() {
        return countValuable;
    }

    public void setCountValuable(Integer countValuable) {
        this.countValuable = countValuable;
    }

    public Boolean getHelpful() {
        return isHelpful;
    }

    public void setHelpful(Boolean helpful) {
        isHelpful = helpful;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Blob getImageUser() {
        return imageUser;
    }

    public void setImageUser(Blob imageUser) {
        this.imageUser = imageUser;
    }

    public Integer getReputationCount() {
        return reputationCount;
    }

    public void setReputationCount(Integer reputationCount) {
        this.reputationCount = reputationCount;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
