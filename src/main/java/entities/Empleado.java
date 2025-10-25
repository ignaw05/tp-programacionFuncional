package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Empleado {
    private String nombre;
    private Departamento departamento;
    private Double salario;
    private int edad;
}
