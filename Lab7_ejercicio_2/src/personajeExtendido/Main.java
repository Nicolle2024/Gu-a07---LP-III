package personajeExtendido;

public class Main {
    public static void main(String[] args) {
        GestorPersonajes gestor = new GestorPersonajes();

        // Agregar personajes iniciales
        System.out.println("\n--- AGREGANDO PERSONAJES ---");
        gestor.agregarPersonaje(new Personaje("Caballero", 4, 2, 4, 2));
        gestor.agregarPersonaje(new Personaje("Guerrero", 2, 4, 2, 4));
        gestor.agregarPersonaje(new Personaje("Arquero", 2, 4, 1, 8));

        // Mostrar personajes
        System.out.println("\n--- LISTA DE PERSONAJES ---");
        gestor.mostrarPersonajes();

        // Modificar TODOS los atributos de un personaje
        System.out.println("\n--- MODIFICAR GUERRERO (TODOS LOS ATRIBUTOS) ---");
        gestor.modificarPersonaje("Guerrero", 3, 5, 2, 4);
        gestor.mostrarPersonajes();

        // - Modificar SOLO un atributo específico
        System.out.println("\n--- MODIFICAR SOLO 'vida' DEL ARQUERO ---");
        gestor.modificarAtributo("Arquero", "vida", 5);
        gestor.mostrarPersonajes();

        // - Mostrar estadísticas
        System.out.println("\n--- MOSTRAR ESTADÍSTICAS ---");
        gestor.mostrarEstadisticas();

        // Eliminar un personaje
        System.out.println("\n--- ELIMINAR CABALLERO ---");
        gestor.borrarPersonaje("Caballero");

        // Mostrar final
        System.out.println("\n--- LISTA FINAL DE PERSONAJES ---");
        gestor.mostrarPersonajes();

        // - Mostrar estadísticas actualizadas
        System.out.println("\n--- ESTADÍSTICAS ACTUALIZADAS ---");
        gestor.mostrarEstadisticas();
    }
}

