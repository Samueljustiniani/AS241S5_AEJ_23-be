package ap1.samuel.justiniani.dto;

import java.util.List;

public class ChatRequest {
    private List<ChatMessage> messages;
    private boolean web_access = false;

    public ChatRequest() {}
    public ChatRequest(List<ChatMessage> messages) {
        this.messages = messages;
    }
    public List<ChatMessage> getMessages() { return messages; }
    public void setMessages(List<ChatMessage> messages) { this.messages = messages; }
    public boolean isWeb_access() { return web_access; }
    public void setWeb_access(boolean web_access) { this.web_access = web_access; }
}
