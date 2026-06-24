/* Maximiliano Parra - Tarea 3 - Opcion B */
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.Scanner;

// Clase CentroApoyo que contiene una lista de estudiantes con sus respectivos cursos y notas, ademas de metodos para verificar si un estudiante esta aprobado o reprobado, mostrar su estado y realizar operaciones matematicas basicas.
public class CentroApoyo {

    public record Estudiante(String nombre, String curso, double nota) {
    }

    //Aca se crea una lista de estudiantes con sus respectivos cursos y notas, ademas se definen dos predicados para verificar si un estudiante esta aprobado o reprobado segun su nota.
    private List<Estudiante> estudiantes;
    private Predicate<Estudiante> aprobado  = e -> e.nota() >= 4.0;
    private Predicate<Estudiante> reprobado = e -> e.nota() < 4.0;

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

    //hace uso de stream para mostrar el estado de un estudiante con el nombre dado, ignora mayusculas y retorna un mensaje con su estado.
    public void mostrarEstadoEstudiante() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = sc.nextLine();

        this.estudiantes.stream()
        .filter(e->e.nombre().equalsIgnoreCase(nombre))
        .findFirst()
        .ifPresentOrElse(e->System.out.println("Estudiante: " + e.nombre()
        + "\n Curso: " + e.curso()
        + "\n Nota: " + e.nota()
        + "\n Estado: " + (this.aprobado.test(e) ? "Aprobado" : "Reprobado" + "\n")),
        ()->System.out.println("Estudiante no encontrado: " + nombre + "\n"));
    }
 
    //hace uso de BinaryOperator para realizar operaciones matematicas basicas entre dos numeros, ademas de manejar excepciones como division por cero y operacion no valida.
    public void calculadora(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el primer numero: ");
        double num1 = sc.nextDouble();

        System.out.print("Ingrese el segundo numero: ");
        double num2 = sc.nextDouble();

        System.out.print("Ingrese la operación (+, -, *, /): \n");
        String operacion = sc.next();

        // Definición de operaciones usando BinaryOperator
        BinaryOperator<Double> suma = (x, y) -> x + y;
        BinaryOperator<Double> resta = (x, y) -> x - y;
        BinaryOperator<Double> multiplicacion = (x, y) -> x * y;
        BinaryOperator<Double> division = (x, y) -> x / y;
        double resultado;

        // Manejo de operaciones y excepciones
        switch (operacion) {
            case "+" -> resultado = suma.apply(num1, num2);
            case "-" -> resultado = resta.apply(num1, num2);
            case "*" -> resultado = multiplicacion.apply(num1, num2);
            case "/" -> {
                if (num2 == 0) {
                    System.out.println("Error: División por cero no permitida.");
                    return;
                }
                resultado = division.apply(num1, num2);}
            default -> {
                System.out.println("Operación no válida");
                return;
            }
        }
        System.out.println("Resultado: " + resultado);
    }

    //hace uso de un menu para mostrar las opciones disponibles al usuario, ademas de manejar la entrada del usuario y llamar a los metodos correspondientes segun la opcion seleccionada.
    public void menu() {
    Scanner sc = new Scanner(System.in);
    int opcion;

    do {
        System.out.println("===== CENTRO DE APOYO =====");
        System.out.println("1. Mostrar estado de estudiante");
        System.out.println("2. Calculadora");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1 -> mostrarEstadoEstudiante();
            case 2 -> calculadora();
            case 0 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida");
        }

    } while (opcion != 0);

    }

    //hace uso del metodo main para crear una instancia de la clase CentroApoyo y llamar al metodo menu para iniciar la aplicacion.
    public static void main(String[] args) {

        CentroApoyo centro = new CentroApoyo();
        centro.menu();
        }
    }