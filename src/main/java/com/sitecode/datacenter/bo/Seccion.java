package com.sitecode.datacenter.bo;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 11/07/18
 */

@Entity
public class Seccion implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idSeccion;
    private Integer orden;
    private String nombre;
    private Integer capacidad;
    private Integer idGrado;

    public Seccion() {
    }

    public Seccion(Integer orden, String nombre, Integer capacidad, Integer idGrado) {
        this.orden = orden;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.idGrado = idGrado;
    }

    public Integer getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seccion seccion = (Seccion) o;

        if (!idSeccion.equals(seccion.idSeccion)) return false;
        if (!orden.equals(seccion.orden)) return false;
        if (!nombre.equals(seccion.nombre)) return false;
        if (!capacidad.equals(seccion.capacidad)) return false;
        return idGrado.equals(seccion.idGrado);
    }

    @Override
    public int hashCode() {
        int result = idSeccion.hashCode();
        result = 31 * result + orden.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + capacidad.hashCode();
        result = 31 * result + idGrado.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Seccion{" +
                "idSeccion=" + idSeccion +
                ", orden=" + orden +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", idGrado=" + idGrado +
                '}';
    }
}
