package ap1.samuel.justiniani.dto;

public class ConsultaRequest {

    private String pregunta;

    public ConsultaRequest() {
    }

    public ConsultaRequest(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}