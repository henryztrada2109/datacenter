package com.sitecode.datacenter.bo;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 10/07/18
 */

@Entity
public class EstadosInscripciones implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idEstado;
    private Integer codigo;
    private String descripcion;

    public EstadosInscripciones() {
    }

    public EstadosInscripciones(Integer codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

        EstadosInscripciones that = (EstadosInscripciones) o;

        if (!idEstado.equals(that.idEstado)) return false;
        if (!codigo.equals(that.codigo)) return false;
        return descripcion.equals(that.descripcion);
    }

    @Override
    public int hashCode() {
        int result = idEstado.hashCode();
        result = 31 * result + codigo.hashCode();
        result = 31 * result + descripcion.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EstadosInscripciones{" +
                "idEstado=" + idEstado +
                ", codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
