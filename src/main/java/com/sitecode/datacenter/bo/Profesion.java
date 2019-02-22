package com.sitecode.datacenter.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 17/08/18
 */

@Entity
public class Profesion implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idProfesion;
    private String descripcion;

    public Profesion() {
    }

    public Profesion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdProfesion() {
        return idProfesion;
    }

    public void setIdProfesion(Integer idProfesion) {
        this.idProfesion = idProfesion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profesion profesion = (Profesion) o;
        return Objects.equals(idProfesion, profesion.idProfesion) &&
                Objects.equals(descripcion, profesion.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfesion, descripcion);
    }

    @Override
    public String toString() {
        return "Profesion{" +
                "idProfesion=" + idProfesion +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}
