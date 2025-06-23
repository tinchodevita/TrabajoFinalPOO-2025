package clasesPrincipales;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private int id;
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;
    private String ubicacion;
    private int capacidadMaxima;
    private List<Asistente> asistentes;

    public Evento(int id, String nombre, String descripcion, LocalDate fecha, LocalTime hora, String ubicacion, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacion = ubicacion;
        this.capacidadMaxima = capacidadMaxima;
        this.asistentes = new ArrayList<>();
    }

    public void agregarAsistente(Asistente a) {
        if (!estaLleno()) {
            asistentes.add(a);
        }
    }

    public void eliminarAsistente(Asistente a) {
        asistentes.remove(a);
    }

    public boolean estaLleno() {
        return asistentes.size() >= capacidadMaxima;
    }

    public int getCantidadAsistentes() {
        return asistentes.size();
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public List<Asistente> getAsistentes() {
        return asistentes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    @Override
    public String toString() {
        return nombre + " - " + fecha.toString() + " " + hora.toString();
    }
}