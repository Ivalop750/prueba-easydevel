package prueba.models;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "t_section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;
}
