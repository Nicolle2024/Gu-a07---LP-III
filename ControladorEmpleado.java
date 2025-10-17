package controlador;

import modelo.Empleado;
import java.io.*;
import java.util.*;

public class ControladorEmpleado {
    private static final String ARCHIVO = "empleados.txt";

    // Lee empleados desde el archivo
    public List<Empleado> leerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    int numero = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    double sueldo = Double.parseDouble(datos[2]);
                    empleados.add(new Empleado(numero, nombre, sueldo));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo al guardar.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return empleados;
    }

    // Guarda todos los empleados en el archivo
    private void guardarEmpleados(List<Empleado> empleados) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Empleado emp : empleados) {
                pw.println(emp.getNumero() + "," + emp.getNombre() + "," + emp.getSueldo());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los empleados: " + e.getMessage());
        }
    }

    // Agrega un nuevo empleado
    public void agregarEmpleado(Empleado empleado) {
        List<Empleado> empleados = leerEmpleados();
        empleados.add(empleado);
        guardarEmpleados(empleados);
        System.out.println("Empleado agregado correctamente.");
    }

    // Busca empleado por número
    public Empleado buscarEmpleado(int numero) {
        List<Empleado> empleados = leerEmpleados();
        for (Empleado emp : empleados) {
            if (emp.getNumero() == numero) {
                return emp;
            }
        }
        return null;
    }

    // Elimina empleado por número
    public void eliminarEmpleado(int numero) {
        List<Empleado> empleados = leerEmpleados();
        boolean eliminado = empleados.removeIf(emp -> emp.getNumero() == numero);
        if (eliminado) {
            guardarEmpleados(empleados);
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("No se encontró un empleado con ese número.");
        }
    }

    // Lista empleados
    public void listarEmpleados() {
        List<Empleado> empleados = leerEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("Lista de empleados:");
            for (Empleado emp : empleados) {
                System.out.println(emp);
            }
        }
    }
}
