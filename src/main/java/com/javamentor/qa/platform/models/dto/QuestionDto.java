package com.javamentor.qa.platform.models.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@ToString
@EqualsAndHashCode
public class QuestionDto {
    private Long id;
    private String title;
    private String username;
    private List<TagDto> tags = new ArrayList<>();
    private Integer reputationCount;
    private Integer viewCount = 0;
    private Integer countAnswer = 0;
    private Integer countValuable = 0;
    private LocalDateTime persistDateTime;

    public QuestionDto() {
    }

    public QuestionDto(Long id, String title, String username, List<TagDto> tags, Integer reputationCount, Integer viewCount, Integer countAnswer, Integer countValuable, LocalDateTime persistDateTime) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.tags = tags;
        this.reputationCount = reputationCount;
        this.viewCount = viewCount;
        this.countAnswer = countAnswer;
        this.countValuable = countValuable;
        this.persistDateTime = persistDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public Integer getReputationCount() {
        return reputationCount;
    }

    public void setReputationCount(Integer reputationCount) {
        this.reputationCount = reputationCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCountAnswer() {
        return countAnswer;
    }

    public void setCountAnswer(Integer countAnswer) {
        this.countAnswer = countAnswer;
    }

    public Integer getCountValuable() {
        return countValuable;
    }

    public void setCountValuable(Integer countValuable) {
        this.countValuable = countValuable;
    }

    public LocalDateTime getPersistDateTime() {
        return persistDateTime;
    }

    public void setPersistDateTime(LocalDateTime persistDateTime) {
        this.persistDateTime = persistDateTime;
    }
}