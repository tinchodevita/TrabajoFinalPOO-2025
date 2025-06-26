import clasesPrincipales.GestorArchivos;
import clasesPrincipales.GestorEventos;
import interfaz.PantallaPrincipal;

public class App {
    public static void main(String[] args) {
        GestorEventos gestor = new GestorEventos();

        // cargar desde archivo
        GestorArchivos.cargarEventos(gestor, "eventos.txt");

        javax.swing.SwingUtilities.invokeLater(() -> {
            new PantallaPrincipal(gestor);
        });

        // guardar al salir
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            GestorArchivos.guardarEventos(gestor.getTodosLosEventos(), "eventos.txt");
        }));
    }
}
