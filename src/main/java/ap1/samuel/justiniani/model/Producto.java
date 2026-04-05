package ap1.samuel.justiniani.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Data
@Table(name = "producto")
public class Producto {

    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "nombre")
    private String nombre;

    @Column(value = "descripcion")
    private String descripcion;

    @Column(value = "precio")
    private Double precio;

    @Column(value = "stock")
    private Integer stock;

    @Column(value = "categoria")
    private String categoria;

    @Column(value = "marca")
    private String marca;

    @Column(value = "codigo")
    private String codigo;

    @Column(value = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(value = "status")
    private Integer status; // 1: activo, 0: inactivo

}