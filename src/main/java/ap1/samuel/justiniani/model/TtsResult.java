package ap1.samuel.justiniani.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Table("tts_result")
public class TtsResult {
    @Id
    private Long id;
    private String inputText;
    private LocalDateTime createdAt;
    private byte[] audio;

    public TtsResult() {}

    public TtsResult(String inputText, LocalDateTime createdAt, byte[] audio) {
        this.inputText = inputText;
        this.createdAt = createdAt;
        this.audio = audio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getInputText() { return inputText; }
    public void setInputText(String inputText) { this.inputText = inputText; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public byte[] getAudio() { return audio; }
    public void setAudio(byte[] audio) { this.audio = audio; }
}
