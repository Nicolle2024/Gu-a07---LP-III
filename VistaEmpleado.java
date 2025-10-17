package vista;

import controlador.ControladorEmpleado;
import modelo.Empleado;
import java.util.Scanner;

public class VistaEmpleado {
    private ControladorEmpleado controlador;
    private Scanner scanner;

    public VistaEmpleado() {
        controlador = new ControladorEmpleado();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ DE EMPLEADOS =====");
            System.out.println("1. Listar todos los empleados");
            System.out.println("2. Agregar un nuevo empleado");
            System.out.println("3. Buscar empleado por número");
            System.out.println("4. Eliminar empleado por número");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> controlador.listarEmpleados();
                case 2 -> agregarEmpleado();
                case 3 -> buscarEmpleado();
                case 4 -> eliminarEmpleado();
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private void agregarEmpleado() {
        try {
            System.out.print("Ingrese número del empleado: ");
            int numero = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese sueldo: ");
            double sueldo = scanner.nextDouble();
            controlador.agregarEmpleado(new Empleado(numero, nombre, sueldo));
        } catch (Exception e) {
            System.out.println("Error al ingresar datos. Intente de nuevo.");
            scanner.nextLine();
        }
    }

    private void buscarEmpleado() {
        System.out.print("Ingrese número del empleado: ");
        int numero = scanner.nextInt();
        Empleado emp = controlador.buscarEmpleado(numero);
        if (emp != null)
            System.out.println("Empleado encontrado: " + emp);
        else
            System.out.println("No se encontró un empleado con ese número.");
    }

    private void eliminarEmpleado() {
        System.out.print("Ingrese número del empleado a eliminar: ");
        int numero = scanner.nextInt();
        controlador.eliminarEmpleado(numero);
    }
}
