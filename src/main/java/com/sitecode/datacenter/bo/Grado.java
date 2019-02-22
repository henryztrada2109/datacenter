package com.sitecode.datacenter.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 11/07/18
 */

@Entity
public class Grado implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idGrado;
    private Integer orden;
    private String nombre;
    private Integer idTipoMenu;
    private Integer cantidadCuotas;
    private BigDecimal costoInscripcion;
    private BigDecimal costoCuota;
    private Integer idRole;

    public Grado() {
    }

    public Grado(Integer orden, String nombre, Integer idTipoMenu, Integer cantidadCuotas, BigDecimal costoInscripcion,
                 BigDecimal costoCuota, Integer idRole) {
        this.orden = orden;
        this.nombre = nombre;
        this.idTipoMenu = idTipoMenu;
        this.cantidadCuotas = cantidadCuotas;
        this.costoInscripcion = costoInscripcion;
        this.costoCuota = costoCuota;
        this.idRole = idRole;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
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

    public Integer getIdTipoMenu() {
        return idTipoMenu;
    }

    public void setIdTipoMenu(Integer idTipoMenu) {
        this.idTipoMenu = idTipoMenu;
    }

    public Integer getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(Integer cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public BigDecimal getCostoInscripcion() {
        return costoInscripcion;
    }

    public void setCostoInscripcion(BigDecimal costoInscripcion) {
        this.costoInscripcion = costoInscripcion;
    }

    public BigDecimal getCostoCuota() {
        return costoCuota;
    }

    public void setCostoCuota(BigDecimal costoCuota) {
        this.costoCuota = costoCuota;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grado grado = (Grado) o;
        return Objects.equals(idGrado, grado.idGrado) &&
                Objects.equals(orden, grado.orden) &&
                Objects.equals(nombre, grado.nombre) &&
                Objects.equals(idTipoMenu, grado.idTipoMenu) &&
                Objects.equals(cantidadCuotas, grado.cantidadCuotas) &&
                Objects.equals(costoInscripcion, grado.costoInscripcion) &&
                Objects.equals(costoCuota, grado.costoCuota) &&
                Objects.equals(idRole, grado.idRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGrado, orden, nombre, idTipoMenu, cantidadCuotas, costoInscripcion, costoCuota, idRole);
    }

    @Override
    public String toString() {
        return "Grado{" +
                "idGrado=" + idGrado +
                ", orden=" + orden +
                ", nombre='" + nombre + '\'' +
                ", idTipoMenu=" + idTipoMenu +
                ", cantidadCuotas=" + cantidadCuotas +
                ", costoInscripcion=" + costoInscripcion +
                ", costoCuota=" + costoCuota +
                ", idRole=" + idRole +
                '}';
    }
}
