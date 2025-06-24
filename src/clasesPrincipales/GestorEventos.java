package clasesPrincipales;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorEventos {
    private List<Evento> eventos;
    private int proximoId = 1;

    public GestorEventos() {
        eventos = new ArrayList<>();
    }

    // crear evento
    public void crearEvento(String nombre, String descripcion, LocalDate fecha, String hora, String ubicacion, int capacidadMaxima) {
        Evento nuevo = new Evento(proximoId++, nombre, descripcion, fecha, java.time.LocalTime.parse(hora), ubicacion, capacidadMaxima);
        eventos.add(nuevo);
    }

    // editar evento
    public void editarEvento(int id, String nombre, String descripcion, LocalDate fecha, String hora, String ubicacion, int capacidadMaxima) {
        Evento evento = buscarEventoPorId(id);
        if (evento != null) {
            evento.setNombre(nombre);
            evento.setDescripcion(descripcion);
            evento.setFecha(fecha);
            evento.setHora(java.time.LocalTime.parse(hora));
            evento.setUbicacion(ubicacion);
            evento.setCapacidadMaxima(capacidadMaxima);
        }
    }

    // eliminar evento
    public void eliminarEvento(int id) {
        eventos.removeIf(e -> e.getId() == id);
    }

    // buscar evento
    public Evento buscarEventoPorId(int id) {
        for (Evento e : eventos) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    // mostrar eventos futuros
    public List<Evento> listarEventosFuturos() {
        List<Evento> futuros = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getFecha().isAfter(LocalDate.now())) {
                futuros.add(e);
            }
        }
        return futuros;
    }
    
    // mostrar eventos pasados
    public List<Evento> listarEventosPasados() {
        List<Evento> pasados = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getFecha().isBefore(LocalDate.now())) {
                pasados.add(e);
            }
        }
        return pasados;
    }

    // traer todos los eventos
    public List<Evento> getTodosLosEventos() {
        return eventos;
    }
}