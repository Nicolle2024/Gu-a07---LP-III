package personajeExtendido;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorPersonajes {
    private List<Personaje> personajes;
    private final String archivo = "personajes.dat";

    public GestorPersonajes() {
        personajes = new ArrayList<>();
        cargarDesdeArchivo();
    }

    public void agregarPersonaje(Personaje p) {
        for (Personaje per : personajes) {
            if (per.getNombre().equalsIgnoreCase(p.getNombre())) {
                System.out.println("El personaje " + p.getNombre() + " ya existe.");
                return;
            }
        }
        personajes.add(p);
        guardarEnArchivo();
        System.out.println("Personaje agregado: " + p.getNombre());
    }

    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        for (Personaje p : personajes) {
            System.out.println(p);
        }
    }

    public void modificarPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.setVida(vida);
                p.setAtaque(ataque);
                p.setDefensa(defensa);
                p.setAlcance(alcance);
                guardarEnArchivo();
                System.out.println("Personaje modificado: " + nombre);
                return;
            }
        }
        System.out.println("No se encontró el personaje: " + nombre);
    }

    // NUEVA FUNCIÓN 1: Modificar un solo atributo
    public void modificarAtributo(String nombre, String atributo, int nuevoValor) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                switch (atributo.toLowerCase()) {
                    case "vida":
                        p.setVida(nuevoValor);
                        break;
                    case "ataque":
                        p.setAtaque(nuevoValor);
                        break;
                    case "defensa":
                        p.setDefensa(nuevoValor);
                        break;
                    case "alcance":
                        p.setAlcance(nuevoValor);
                        break;
                    default:
                        System.out.println("Atributo no válido. Usa: vida, ataque, defensa o alcance.");
                        return;
                }
                guardarEnArchivo();
                System.out.println("Atributo '" + atributo + "' actualizado para " + nombre);
                return;
            }
        }
        System.out.println("No se encontró el personaje: " + nombre);
    }

    public void borrarPersonaje(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                personajes.remove(p);
                guardarEnArchivo();
                System.out.println("Personaje eliminado: " + nombre);
                return;
            }
        }
        System.out.println("No se encontró el personaje: " + nombre);
    }

    // NUEVA FUNCIÓN 2: Mostrar estadísticas
    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para mostrar estadísticas.");
            return;
        }

        int total = personajes.size();
        double vidaProm = 0, ataqueProm = 0, defensaProm = 0, alcanceProm = 0;

        for (Personaje p : personajes) {
            vidaProm += p.getVida();
            ataqueProm += p.getAtaque();
            defensaProm += p.getDefensa();
            alcanceProm += p.getAlcance();
        }

        System.out.println("\n--- ESTADÍSTICAS GENERALES ---");
        System.out.println("Total de personajes: " + total);
        System.out.println("Promedio de Vida: " + (vidaProm / total));
        System.out.println("Promedio de Ataque: " + (ataqueProm / total));
        System.out.println("Promedio de Defensa: " + (defensaProm / total));
        System.out.println("Promedio de Alcance: " + (alcanceProm / total));
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(personajes);
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    private void cargarDesdeArchivo() {
        File f = new File(archivo);
        if (!f.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            personajes = (List<Personaje>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }
    }
}

