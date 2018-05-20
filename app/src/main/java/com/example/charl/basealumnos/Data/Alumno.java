package com.example.charl.basealumnos.Data;

public class Alumno {

    private String carnet;
    private String nombre;
    private String nota;

    public Alumno(){

    }

    public Alumno(String carnet, String nombre, String nota) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.nota = nota;
    }

    public Alumno(String carnet, String nombre) {
        this.carnet = carnet;
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
