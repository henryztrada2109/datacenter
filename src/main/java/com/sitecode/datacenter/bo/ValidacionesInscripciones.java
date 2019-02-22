package com.sitecode.datacenter.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 11/07/18
 */

@Entity
public class ValidacionesInscripciones implements Serializable{

    private static final long serialVersionUID = 7176428109269469686L;

    @Id
    private Integer idRole;
    private Boolean periodo;
    private Boolean nombres;
    private Boolean apellidos;
    private Boolean genero;
    private Boolean fechaNacimiento;
    private Boolean telefonoCasa;
    private Boolean telefonoCelular;
    private Boolean correoElectronico;
    private Boolean profesion;
    private Boolean estadoCivil;
    private Boolean ocupacion;
    private Boolean tipoIdentificacion;
    private Boolean identificacion;
    private Boolean pais;
    private Boolean departamento;
    private Boolean municipio;
    private Boolean direccion;

    public ValidacionesInscripciones() {
    }

    public ValidacionesInscripciones(Integer idRole, Boolean periodo, Boolean nombres, Boolean apellidos,
                                     Boolean genero, Boolean fechaNacimiento, Boolean telefonoCasa,
                                     Boolean telefonoCelular, Boolean correoElectronico, Boolean profesion,
                                     Boolean estadoCivil, Boolean ocupacion, Boolean tipoIdentificacion,
                                     Boolean identificacion, Boolean pais, Boolean departamento, Boolean municipio,
                                     Boolean direccion) {
        this.idRole = idRole;
        this.periodo = periodo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.telefonoCasa = telefonoCasa;
        this.telefonoCelular = telefonoCelular;
        this.correoElectronico = correoElectronico;
        this.profesion = profesion;
        this.estadoCivil = estadoCivil;
        this.ocupacion = ocupacion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.pais = pais;
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Boolean getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Boolean periodo) {
        this.periodo = periodo;
    }

    public Boolean getNombres() {
        return nombres;
    }

    public void setNombres(Boolean nombres) {
        this.nombres = nombres;
    }

    public Boolean getApellidos() {
        return apellidos;
    }

    public void setApellidos(Boolean apellidos) {
        this.apellidos = apellidos;
    }

    public Boolean getGenero() {
        return genero;
    }

    public void setGenero(Boolean genero) {
        this.genero = genero;
    }

    public Boolean getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Boolean fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(Boolean telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public Boolean getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(Boolean telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public Boolean getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(Boolean correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Boolean getProfesion() {
        return profesion;
    }

    public void setProfesion(Boolean profesion) {
        this.profesion = profesion;
    }

    public Boolean getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Boolean estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Boolean getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Boolean ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Boolean getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Boolean tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Boolean getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Boolean identificacion) {
        this.identificacion = identificacion;
    }

    public Boolean getPais() {
        return pais;
    }

    public void setPais(Boolean pais) {
        this.pais = pais;
    }

    public Boolean getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Boolean departamento) {
        this.departamento = departamento;
    }

    public Boolean getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Boolean municipio) {
        this.municipio = municipio;
    }

    public Boolean getDireccion() {
        return direccion;
    }

    public void setDireccion(Boolean direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidacionesInscripciones that = (ValidacionesInscripciones) o;
        return Objects.equals(idRole, that.idRole) &&
                Objects.equals(periodo, that.periodo) &&
                Objects.equals(nombres, that.nombres) &&
                Objects.equals(apellidos, that.apellidos) &&
                Objects.equals(genero, that.genero) &&
                Objects.equals(fechaNacimiento, that.fechaNacimiento) &&
                Objects.equals(telefonoCasa, that.telefonoCasa) &&
                Objects.equals(telefonoCelular, that.telefonoCelular) &&
                Objects.equals(correoElectronico, that.correoElectronico) &&
                Objects.equals(profesion, that.profesion) &&
                Objects.equals(estadoCivil, that.estadoCivil) &&
                Objects.equals(ocupacion, that.ocupacion) &&
                Objects.equals(tipoIdentificacion, that.tipoIdentificacion) &&
                Objects.equals(identificacion, that.identificacion) &&
                Objects.equals(pais, that.pais) &&
                Objects.equals(departamento, that.departamento) &&
                Objects.equals(municipio, that.municipio) &&
                Objects.equals(direccion, that.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, periodo, nombres, apellidos, genero, fechaNacimiento, telefonoCasa, telefonoCelular,
                correoElectronico, profesion, estadoCivil, ocupacion, tipoIdentificacion, identificacion, pais,
                departamento, municipio, direccion);
    }

    @Override
    public String toString() {
        return "ValidacionesInscripciones{" +
                "idRole=" + idRole +
                ", periodo=" + periodo +
                ", nombres=" + nombres +
                ", apellidos=" + apellidos +
                ", genero=" + genero +
                ", fechaNacimiento=" + fechaNacimiento +
                ", telefonoCasa=" + telefonoCasa +
                ", telefonoCelular=" + telefonoCelular +
                ", correoElectronico=" + correoElectronico +
                ", profesion=" + profesion +
                ", estadoCivil=" + estadoCivil +
                ", ocupacion=" + ocupacion +
                ", tipoIdentificacion=" + tipoIdentificacion +
                ", identificacion=" + identificacion +
                ", pais=" + pais +
                ", departamento=" + departamento +
                ", municipio=" + municipio +
                ", direccion=" + direccion +
                '}';
    }
}
