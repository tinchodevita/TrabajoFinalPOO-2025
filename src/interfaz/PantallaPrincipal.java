package interfaz;

import clasesPrincipales.Evento;
import clasesPrincipales.GestorEventos;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PantallaPrincipal extends JFrame {

    private GestorEventos gestor;
    private DefaultListModel<Evento> modeloEventos;
    private JList<Evento> listaEventos;

    public PantallaPrincipal(GestorEventos gestor) {
        this.gestor = gestor;

        // caracteristicas generales
        setTitle("Gestión de Eventos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel principal con espacios
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // titulo centrado
        JLabel titulo = new JLabel("Lista de eventos");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        // lista de eventos
        modeloEventos = new DefaultListModel<>();
        listaEventos = new JList<>(modeloEventos);
        JScrollPane scroll = new JScrollPane(listaEventos);
        panel.add(scroll, BorderLayout.CENTER);

        // botones
        JButton botonVerDetalles = new JButton("Ver detalles");
        JButton botonNuevoEvento = new JButton("Nuevo evento");

        botonVerDetalles.addActionListener(e -> {
            Evento eventoSeleccionado = listaEventos.getSelectedValue();
            if (eventoSeleccionado != null) {
                new VistaEvento(gestor, eventoSeleccionado);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccioná un evento.");
            }
        });

        botonNuevoEvento.addActionListener(e -> {
            new FormularioEvento(gestor, this);
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonVerDetalles);
        panelBotones.add(botonNuevoEvento); // se implementa después
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);
        cargarEventos();
        setVisible(true);
    }

    // carga de eventos
    void cargarEventos() {
        modeloEventos.clear();
        List<Evento> eventos = gestor.getTodosLosEventos();
        for (Evento evento : eventos) {
            modeloEventos.addElement(evento);
        }
    }
}
