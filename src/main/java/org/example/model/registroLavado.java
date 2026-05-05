package org.example.model;

public class registroLavado {
    private int registroID;
    private int vehiculoID;
    private int servicioID;
    private String fecha;

    public registroLavado() {}

    public registroLavado(int registroID, int vehiculoID, int servicioID, String fecha) {
        this.registroID = registroID;
        this.vehiculoID = vehiculoID;
        this.servicioID = servicioID;
        this.fecha = fecha;
    }

    public int getRegistroID() { return registroID; }
    public void setRegistroID(int registroID) { this.registroID = registroID; }
    public int getVehiculoID() { return vehiculoID; }
    public void setVehiculoID(int vehiculoID) { this.vehiculoID = vehiculoID; }
    public int getServicioID() { return servicioID; }
    public void setServicioID(int servicioID) { this.servicioID = servicioID; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}