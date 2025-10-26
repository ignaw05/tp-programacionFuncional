import entities.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Curso curso1 = new Curso("DSW", "Alberto");
        Curso curso2 = new Curso("Diseno", "Marquitos");
        Alumno al1 = new Alumno("Igna", 10, curso1);
        Alumno al2 = new Alumno("Juan", 6, curso1);
        Alumno al3 = new Alumno("Nico", 5, curso2);
        Alumno al4 = new Alumno("Mati", 9, curso2);
        Alumno al5 = new Alumno("Ale", 7, curso1);


        List<Alumno> alumnos = List.of(al1, al2, al3, al4, al5);
        // aprobados
        List<Alumno> aprobados = alumnos.stream().
                filter(x -> x.getNota() >= 7).
                toList();

        List aprobadosOrd = aprobados.stream().
                map(Alumno::getNombre).
                map(String::toUpperCase).
                sorted().
                toList();
        System.out.println("APROBADOS: " + aprobadosOrd);

        // promedio
        float promedio = (float) alumnos.stream().
                map(Alumno::getNota).
                reduce(0, (a, b) -> a + b) / alumnos.size();

        System.out.println("\nPROMEDIO: " + promedio);

        // alumnos por curso
        Map<Curso, List<Alumno>> porCurso = alumnos.stream().
                collect(Collectors.
                    groupingBy(Alumno::getCurso));

        porCurso.forEach((curso, listaAlumnos) -> {
            System.out.println("Curso: " + curso.getNombre());
            listaAlumnos.forEach(a -> System.out.println(" - " + a.getNombre()));
        });

        List<Integer> mejoresProm = alumnos.stream().
                map(Alumno::getNota).
                sorted(Comparator.reverseOrder()).
                limit(3).
                toList();

        System.out.println("Mejores promedios: " + mejoresProm);

        // EJ 2
        System.out.println("\n\n--------------EJERCICIO 2-----------------");
        Categoria cat1 = new Categoria("Fiambres");
        Categoria cat2 = new Categoria("Bebidas");
        Producto prod1 = new Producto("Jamon crudo", 10, 190F, cat1);
        Producto prod2 = new Producto("Queso Brye", 50, 250F, cat1);
        Producto prod3 = new Producto("Salamin", 40, 80F, cat1);
        Producto prod4 = new Producto("CocaCola", 100, 350F, cat2);
        Producto prod5 = new Producto("Gatorade Vidrio", 20, 560F, cat2);

        List<Producto> productos = List.of(prod1, prod2, prod3, prod4, prod5);

        System.out.println("\nProductos con precio mayor a $100");
        List<Producto> prodMas1000 = productos.stream().
                filter(prod -> prod.getPrecio() > 100).
                toList();

        List<String> prod100 = prodMas1000.stream().
                map(x -> new String(x.getNombre() + ": $" + x.getPrecio())).toList();

        System.out.println(prod100);

        System.out.println("\nProductos por categoria");
        Map<Categoria, List<Producto>> prodCat = productos.stream().
                collect(Collectors.groupingBy(Producto::getCategoria));

        prodCat.forEach((categoria, listaProd) -> {
            System.out.println("Categoria: " + categoria.getNombre());
            listaProd.forEach(a -> System.out.println(a.getNombre()+ " - " + a.getStock()));
        });

        System.out.println("\nTodos los productos");
        String allProd = productos.stream().
                map(a -> {
                    return a.getNombre() + " $" + a.getPrecio();
                })
                .collect(Collectors.joining("; "));

        System.out.println(allProd);

        System.out.println("\nPrecio promedio general");
        Float promGeneral = productos.stream().
                map(Producto::getPrecio).
                reduce(0F, (a,b) -> a+b)/productos.size();
        System.out.println("$"+promGeneral);

        System.out.println("\nPrecio promedio por categoria");

        prodCat.forEach((categoria, listaProd) -> {
            System.out.println("Categoria: " + categoria.getNombre());
            Float promCat = listaProd.stream().
                    map(Producto::getPrecio).
                    reduce(0F,(a,b)->a+b)/ listaProd.size();
            System.out.println("$"+promCat);
        });


        System.out.println("\n\n--------------EJERCICIO 3-----------------");
        Autor aut1 = new Autor("Cal Newport");
        Autor aut2 = new Autor("Hernandez");
        Libro libro1 = new Libro("DeepWork",aut1,240,32000D);
        Libro libro2 = new Libro("Martin Fierro",aut2,302,39000D);
        Libro libro3 = new Libro("Design System Interview",aut1,450,31000D);
        Libro libro4 = new Libro("El monje",aut2,350,35000D);
        Libro libro5 = new Libro("4 acuerdos",aut1,120,25000D);

        List<Libro> libros = List.of(libro1,libro2,libro3,libro4,libro5);

        System.out.println("\nLibros con +300 paginas");
        List<Libro> librosMas300 = libros.stream().
                filter(libro -> libro.getPaginas() > 300).
                sorted(Comparator.comparingInt(Libro::getPaginas).reversed()).
                toList();

        librosMas300.stream().forEach(l -> System.out.println(l.getTitulo()+" - Pags: "+l.getPaginas()));

        System.out.println("\nPromedio de paginas");
        Float promPags = (float) libros.stream().
                map(Libro::getPaginas).
                reduce(0, (a,b) -> a+b)/libros.size();

        System.out.println(promPags);

        System.out.println("\nLibros por autor");
        Map<Autor,List<Libro>> librosAutor = libros.stream().
                collect(Collectors.groupingBy(Libro::getAutor));

        librosAutor.forEach((autor,librosAut) -> {
            System.out.println(autor.getNombre());
            System.out.println("Cantidad: "+librosAut.stream().count());
        });

        System.out.println("\nLibro mas caro");
        List<Libro> libroCaro = libros.stream().
                sorted(Comparator.comparing(Libro::getPrecio).reversed()).
                limit(1).toList();

        System.out.println(libroCaro.get(0).getTitulo()+" $"+libroCaro.get(0).getPrecio());

        System.out.println("\n\n--------------EJERCICIO 3-----------------");
        Departamento depto1 = new Departamento("Finanzas");
        Departamento depto2 = new Departamento("Programacion");
        Empleado emp1 = new Empleado("Juan",depto1,1900D,48);
        Empleado emp2 = new Empleado("Maxi",depto1,1300D,21);
        Empleado emp3 = new Empleado("Igna",depto2,5000D,36);
        Empleado emp4 = new Empleado("Pedro",depto2,4500D,20);
        Empleado emp5 = new Empleado("Luis",depto2,7000D,27);

        List<Empleado> empleados = List.of(emp1,emp2,emp3,emp4,emp5);

        System.out.println("\nEmpleados con salario > 2000");
        List<Empleado> empleados2000 = empleados.stream().
                filter(empleado -> empleado.getSalario() > 2000D).
                sorted(Comparator.comparing(Empleado::getSalario).reversed()).toList();

        empleados2000.forEach(empleado -> System.out.println(empleado.getNombre() + " - $" + empleado.getSalario()));

        System.out.println("\nSalario promedio");
        Double salarioProm = empleados.stream().
                map(Empleado::getSalario).
                reduce(0D,(a,b) -> a + b)/empleados.size();
        System.out.println(salarioProm);

        System.out.println("\nEmpleados por departamento");
        Map<Departamento,List<Empleado>> empDepto = empleados.stream().
                collect(Collectors.groupingBy(Empleado::getDepartamento));

        empDepto.forEach((depto,empleado) -> {
            System.out.println(depto.getNombre());
            System.out.println("$"+empleado.stream().
                    map(Empleado::getSalario).
                    reduce(0D,(a,b) -> a + b));
        });

        System.out.println("\nEmpleado mas jovenes");
        List<Empleado> empJovenes = empleados.stream().
                sorted(Comparator.comparing(Empleado::getEdad)).
                limit(2).
                toList();

        empJovenes.forEach(empleado -> System.out.println(empleado.getNombre()+" - edad: "+empleado.getEdad()));





    }
}
