package ap1.samuel.justiniani.dto;

import java.time.LocalDateTime;

public class TtsHistoryItem {

    private Long id;
    private String inputText;
    private LocalDateTime createdAt;
    private Integer status;

    public TtsHistoryItem() {
    }

    public TtsHistoryItem(Long id, String inputText, LocalDateTime createdAt, Integer status) {
        this.id = id;
        this.inputText = inputText;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}