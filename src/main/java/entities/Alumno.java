package entities;
import lombok.*;

@Getter @Setter
public class Alumno {
    private String nombre;
    private int nota;
    private Curso curso;

    public Alumno(String nombre, int nota, Curso curso) {
        this.nombre = nombre;
        this.nota = nota;
        this.curso = curso;
    }

}
