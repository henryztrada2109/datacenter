package com.sitecode.datacenter.bo;

import com.sitecode.datadto.datacenter.request.PeriodoRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 10/07/18
 */

@Entity
public class Periodo implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    @GeneratedValue
    private Integer idPeriodo;
    private String descripcion;
    private Date fechaInicial;
    private Date fechaFinal;
    private Boolean periodoActual;

    public Periodo() {
    }

    public Periodo(PeriodoRequest periodoRequest) {
        this.idPeriodo = periodoRequest.getIdPeriodo();
        this.descripcion = periodoRequest.getDescripcion();
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.set(periodoRequest.getAnioInicio(), periodoRequest.getMesInicio(), periodoRequest.getDiaInicio());
        this.fechaInicial = fechaInicial.getTime();
        Calendar fechaFinal = Calendar.getInstance();
        fechaFinal.set(periodoRequest.getAnioFin(), periodoRequest.getMesFin(), periodoRequest.getDiaFin());
        this.fechaFinal = fechaFinal.getTime();
        this.periodoActual = periodoRequest.getPeriodoActual();
    }

    public Periodo(String descripcion, Date fechaInicial, Date fechaFinal, Boolean periodoActual) {
        this.descripcion = descripcion;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.periodoActual = periodoActual;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Boolean getPeriodoActual() {
        return periodoActual;
    }

    public void setPeriodoActual(Boolean periodoActual) {
        this.periodoActual = periodoActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Periodo periodo = (Periodo) o;

        if (!idPeriodo.equals(periodo.idPeriodo)) return false;
        if (!descripcion.equals(periodo.descripcion)) return false;
        if (!fechaInicial.equals(periodo.fechaInicial)) return false;
        if (!fechaFinal.equals(periodo.fechaFinal)) return false;
        return periodoActual.equals(periodo.periodoActual);
    }

    @Override
    public int hashCode() {
        int result = idPeriodo.hashCode();
        result = 31 * result + descripcion.hashCode();
        result = 31 * result + fechaInicial.hashCode();
        result = 31 * result + fechaFinal.hashCode();
        result = 31 * result + periodoActual.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "idPeriodo=" + idPeriodo +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicial=" + fechaInicial +
                ", fechaFinal=" + fechaFinal +
                ", periodoActual=" + periodoActual +
                '}';
    }
}
