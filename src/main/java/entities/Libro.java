package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Libro {
    private String titulo;
    private Autor autor;
    private int paginas;
    private Double precio;
}
