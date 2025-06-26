package interfaz;

import clasesPrincipales.Evento;
import clasesPrincipales.GestorEventos;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PantallaPrincipal extends JFrame {

    private GestorEventos gestor;
    private DefaultListModel<Evento> modeloFuturos;
    private DefaultListModel<Evento> modeloPasados;
    private JList<Evento> listaFuturos;
    private JList<Evento> listaPasados;

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
        modeloFuturos = new DefaultListModel<>();
        modeloPasados = new DefaultListModel<>();
        listaFuturos = new JList<>(modeloFuturos);
        listaPasados = new JList<>(modeloPasados);

        JScrollPane scrollFuturos = new JScrollPane(listaFuturos);
        JScrollPane scrollPasados = new JScrollPane(listaPasados);
        JTabbedPane pestañas = new JTabbedPane();

        pestañas.addTab("Futuros", scrollFuturos);
        pestañas.addTab("Pasados", scrollPasados);

        panel.add(pestañas, BorderLayout.CENTER);

        // botones
        JButton botonVerDetalles = new JButton("Ver detalles");
        JButton botonNuevoEvento = new JButton("Nuevo evento");
        JButton botonEliminarEvento = new JButton("Eliminar evento");
        JButton botonEditarEvento = new JButton("Editar evento");

        // accion ver detalles evento
        botonVerDetalles.addActionListener(e -> {
            Evento seleccionado = listaFuturos.getSelectedValue();
            if (seleccionado == null) {
                seleccionado = listaPasados.getSelectedValue();
            }

            if (seleccionado != null) {
                new VistaEvento(gestor, seleccionado);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccioná un evento para ver los detalles.");
            }
        });

        // accion nuevo evento
        botonNuevoEvento.addActionListener(e -> {
            new FormularioEvento(gestor, this);
        });

        // accion eliminar evento
        botonEliminarEvento.addActionListener(e -> {
            Evento seleccionado = listaFuturos.getSelectedValue();
            if (seleccionado == null) {
                seleccionado = listaPasados.getSelectedValue();
            }

            if (seleccionado != null) {
                int confirmacion = JOptionPane.showConfirmDialog(
                        this,
                        "¿Estás seguro de eliminar este evento?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    gestor.eliminarEvento(seleccionado.getId());
                    cargarEventos(); // refresca la lista
                    JOptionPane.showMessageDialog(this, "🗑 Evento eliminado correctamente.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccioná un evento primero.");
            }
        });

        // accion editar evento
        botonEditarEvento.addActionListener(e -> {
            Evento seleccionado = listaFuturos.getSelectedValue();
            if (seleccionado == null) {
                seleccionado = listaPasados.getSelectedValue();
            }

            if (seleccionado != null) {
                new FormularioEvento(gestor, this, seleccionado);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccioná un evento primero.");
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonVerDetalles);
        panelBotones.add(botonNuevoEvento);
        panelBotones.add(botonEliminarEvento);
        panelBotones.add(botonEditarEvento);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);
        cargarEventos();
        setVisible(true);
    }

    // carga de eventos
    public void cargarEventos() {
        modeloFuturos.clear();
        modeloPasados.clear();

        for (Evento e : gestor.getTodosLosEventos()) {
            if (e.getFecha().isAfter(java.time.LocalDate.now())) {
                modeloFuturos.addElement(e);
            } else {
                modeloPasados.addElement(e);
            }
        }
    }

}
