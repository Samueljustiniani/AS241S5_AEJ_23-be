package ap1.samuel.justiniani.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table(name = "consulta")
public class Consulta {

    @Id
    @Column("id")
    private Long id;

    @Column("pregunta")
    private String pregunta;

    @Column("respuesta")
    private String respuesta;

    @Column("fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column("status")
    private Integer status;
}