package personaje;

public class Main {
    public static void main(String[] args) {
        GestorPersonajes gestor = new GestorPersonajes();

        // Agregar personajes
        gestor.agregarPersonaje(new Personaje("Caballero", 4, 2, 4, 2));
        gestor.agregarPersonaje(new Personaje("Guerrero", 2, 4, 2, 4));
        gestor.agregarPersonaje(new Personaje("Arquero", 2, 4, 1, 8));

        // Mostrar personajes
        System.out.println("\n--- LISTA DE PERSONAJES ---");
        gestor.mostrarPersonajes();

        // Modificar
        System.out.println("\n--- MODIFICAR GUERRERO ---");
        gestor.modificarPersonaje("Guerrero", 3, 5, 2, 4);

        // Mostrar actualización
        gestor.mostrarPersonajes();

        // Eliminar
        System.out.println("\n--- ELIMINAR CABALLERO ---");
        gestor.borrarPersonaje("Caballero");

        // Mostrar final
        gestor.mostrarPersonajes();
    }
}
