package com.sitecode.datacenter.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 22/08/18
 */

@Entity
public class EstadoCivil implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idEstado;
    private String descripcion;

    public EstadoCivil() {
    }

    public EstadoCivil(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
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
        EstadoCivil that = (EstadoCivil) o;
        return Objects.equals(idEstado, that.idEstado) &&
                Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstado, descripcion);
    }

    @Override
    public String toString() {
        return "EstadoCivil{" +
                "idEstado=" + idEstado +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
