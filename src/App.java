import clasesPrincipales.GestorEventos;
import interfaz.PantallaPrincipal;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        GestorEventos gestor = new GestorEventos();

        // evento de prueba opcional
        gestor.crearEvento(
                "Feria de ciencia",
                "Proyectos escolares de distintas escuelas",
                LocalDate.of(2025, 7, 10),
                "09:00",
                "Escuela Técnica Nº3",
                100
        );

        // lanzar interfaz principal
        javax.swing.SwingUtilities.invokeLater(() -> {
            new PantallaPrincipal(gestor);
        });
    }
}
