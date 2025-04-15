package com.example.velozerfit20;

public class Ejercicio {
    private String nombre;
    private String descripcion;
    private String urlVideo;
    private int icono;  // Esto es para almacenar el ícono del ejercicio

    // Constructor
    public Ejercicio(String nombre, String descripcion, String urlVideo, int icono) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlVideo = urlVideo;
        this.icono = icono;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }
}
