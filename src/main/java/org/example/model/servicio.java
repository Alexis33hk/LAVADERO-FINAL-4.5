package org.example.model;

public class servicio {
    private int servicioID;
    private String nombreServicio;
    private double precio;

    public servicio() {}

    public servicio(int servicioID, String nombreServicio, double precio) {
        this.servicioID = servicioID;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
    }

    public int getServicioID() { return servicioID; }
    public void setServicioID(int servicioID) { this.servicioID = servicioID; }
    public String getNombreServicio() { return nombreServicio; }
    public void setNombreServicio(String nombreServicio) { this.nombreServicio = nombreServicio; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}