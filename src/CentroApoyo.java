import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.Scanner;

public class CentroApoyo {

    public record Estudiante(String nombre, String curso, double nota) {
    }

    //Aca se crea una lista de estudiantes con sus respectivos cursos y notas, ademas se definen dos predicados para verificar si un estudiante esta aprobado o reprobado segun su nota.
    private List<Estudiante> estudiantes;
    private Predicate<Estudiante>           aprobado  = e -> e.nota() >= 4.0;
    private Predicate<Estudiante>           reprobado = e -> e.nota() < 4.0;

    public CentroApoyo() {
        estudiantes = new ArrayList<>();

        this.estudiantes.add(new Estudiante("Ana", "PTEC102", 6.1));
        this.estudiantes.add(new Estudiante("Luis", "PTEC102", 3.9));
        this.estudiantes.add(new Estudiante("Maria", "CINF101", 5.5));
        this.estudiantes.add(new Estudiante("Pedro", "MAT100", 4.8));
        this.estudiantes.add(new Estudiante("Camila", "PTEC102", 6.7));
        this.estudiantes.add(new Estudiante("Joaquin", "CINF101", 2.9));
        this.estudiantes.add(new Estudiante("Fernanda", "MAT100", 5.0));
        this.estudiantes.add(new Estudiante("Diego", "PTEC102", 4.3));
        this.estudiantes.add(new Estudiante("Valentina", "CINF101", 6.4));
        this.estudiantes.add(new Estudiante("Sebastian", "MAT100", 3.1));
        this.estudiantes.add(new Estudiante("Francisca", "PTEC102", 5.8));
        this.estudiantes.add(new Estudiante("Tomas", "CINF101", 4.2));
        this.estudiantes.add(new Estudiante("Daniela", "MAT100", 6.0));
        this.estudiantes.add(new Estudiante("Benjamin", "PTEC102", 2.5));
        this.estudiantes.add(new Estudiante("Antonia", "CINF101", 5.1));
        this.estudiantes.add(new Estudiante("Vicente", "MAT100", 4.9));
        this.estudiantes.add(new Estudiante("Catalina", "PTEC102", 6.9));
        this.estudiantes.add(new Estudiante("Gabriel", "CINF101", 3.8));
        this.estudiantes.add(new Estudiante("Martina", "MAT100", 5.7));
        this.estudiantes.add(new Estudiante("Agustin", "PTEC102", 4.1));
        this.estudiantes.add(new Estudiante("Isidora", "CINF101", 6.3));
        this.estudiantes.add(new Estudiante("Maximiliano", "MAT100", 2.7));
        this.estudiantes.add(new Estudiante("Renata", "PTEC102", 5.4));
        this.estudiantes.add(new Estudiante("Cristobal", "CINF101", 4.6));
        this.estudiantes.add(new Estudiante("Emilia", "MAT100", 6.5));
        this.estudiantes.add(new Estudiante("Matias", "PTEC102", 3.4));
        this.estudiantes.add(new Estudiante("Amanda", "CINF101", 5.9));
        this.estudiantes.add(new Estudiante("Ignacio", "MAT100", 4.4));
        this.estudiantes.add(new Estudiante("Josefa", "PTEC102", 6.2));
        this.estudiantes.add(new Estudiante("Felipe", "CINF101", 3.3));
    }

    //hace uso de stream para verificar si un estudiante con el nombre dado pertenece al curso especificado, ignora mayusculas y retorna booleano.
    public boolean perteneceACurso(String nombre, String curso) {
        boolean pertenece=this.estudiantes.stream()
        .filter(e->e.nombre().equalsIgnoreCase(nombre))
        .anyMatch(e->e.curso().equalsIgnoreCase(curso));
        return pertenece;
    }

    //hace uso de stream para verificar si un estudiante con el nombre esta aprobado (nota>=4.0), ignora mayusculas y retorna booleano.
    public boolean estaAprobado(String nombre) {
        boolean aprobado=this.estudiantes.stream()
        .filter(e->e.nombre().equalsIgnoreCase(nombre))
        .anyMatch(e->this.aprobado.test(e));
        return aprobado;
    }

    //hace uso de stream para verificar si un estudiante con el nombre esta reprobado (nota<4.0), ignora mayusculas y retorna booleano.
    public boolean estaReprobado(String nombre) {
        boolean reprobado=this.estudiantes.stream()
        .filter(e->e.nombre().equalsIgnoreCase(nombre))
        .anyMatch(e->this.reprobado.test(e));
        return reprobado;
    }

    public void mostrarEstadoEstudiante() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = sc.nextLine();

        this.estudiantes.stream()
        .filter(e->e.nombre().equalsIgnoreCase(nombre))
        .findFirst()
        .ifPresentOrElse(e->System.out.println("Estudiante: " + e.nombre()
        + ", Curso: " + e.curso()
        + ", Nota: " + e.nota()
        + ", Estado: " + (this.aprobado.test(e) ? "Aprobado" : "Reprobado")), 
        ()->System.out.println("Estudiante no encontrado: " + nombre));
    }

    public void Calculadora(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el primer numero: ");
        double num1 = sc.nextDouble();

        System.out.print("Ingrese el segundo numero: ");
        double num2 = sc.nextDouble();

        System.out.print("Ingrese la operación (+, -, *, /): ");
        String operacion = sc.next();

        UnaryOperator<Double> suma = x -> x + num2;
        UnaryOperator<Double> resta = x -> x - num2;
        UnaryOperator<Double> multiplicacion = x -> x * num2;
        UnaryOperator<Double> division = x -> x / num2;

        double resultado;
        switch (operacion) {
            case "+" -> resultado = suma.apply(num1);
            case "-" -> resultado = resta.apply(num1);
            case "*" -> resultado = multiplicacion.apply(num1);
            case "/" -> resultado = division.apply(num1);
            default -> {
                System.out.println("Operación no válida");
                return;
            }
        }
        System.out.println("Resultado: " + resultado);
    }
    public static void main(String[] args) {

        CentroApoyo centro = new CentroApoyo();

        System.out.println(centro.perteneceACurso("Ana", "PTEC102"));
        System.out.println(centro.perteneceACurso("Luis", "MAT100"));

    }
}