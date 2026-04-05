package ap1.samuel.justiniani.dto;

public class TextToSpeechRequest {
    private String input;
    private String instructions;
    private String model = "tts-1";
    private String voice = "alloy";

    public String getInput() { return input; }
    public void setInput(String input) { this.input = input; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getVoice() { return voice; }
    public void setVoice(String voice) { this.voice = voice; }
}
