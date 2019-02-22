package com.sitecode.datacenter.bs.service.impl;

import com.sitecode.datacenter.bs.repository.ValidacionesInscripcionesRepository;
import com.sitecode.datacenter.bs.service.ValidacionesInscripcionesService;
import com.sitecode.datacenter.bo.ValidacionesInscripciones;
import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.datacenter.ValidacionesInscripcionesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 22/06/18
 */

@Service
public class ValidacionesInscripcionesServiceImpl implements ValidacionesInscripcionesService {

    @Autowired

    private final ValidacionesInscripcionesRepository validacionesRepository;

    @Autowired
    public ValidacionesInscripcionesServiceImpl(ValidacionesInscripcionesRepository validacionesRepository){
        Assert.notNull(validacionesRepository,"ValidacionesInscripcionesRepository no puede ser nulo.");
        this.validacionesRepository=validacionesRepository;
    }

    /*Get*/
    @Override
    public ValidacionesInscripcionesDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new ValidacionesInscripcionesDTO(1,"Codigo de registro no puede ser nulo.");
        }
        ValidacionesInscripciones response = validacionesRepository.findOne(codigo);
        if(response == null){
            return new ValidacionesInscripcionesDTO(2,"No se encontro registro.");
        }else{
            ValidacionesInscripcionesDTO validacion = new ValidacionesInscripcionesDTO();
            validacion.setIdRole(response.getIdRole());
            validacion.setPeriodo(response.getPeriodo());
            validacion.setNombres(response.getNombres());
            validacion.setApellidos(response.getApellidos());
            validacion.setGenero(response.getGenero());
            validacion.setFechaNacimiento(response.getFechaNacimiento());
            validacion.setTelefonoCasa(response.getTelefonoCasa());
            validacion.setTelefonoCelular(response.getTelefonoCelular());
            validacion.setCorreoElectronico(response.getCorreoElectronico());
            validacion.setProfesion(response.getProfesion());
            validacion.setEstadoCivil(response.getEstadoCivil());
            validacion.setOcupacion(response.getOcupacion());
            validacion.setTipoIdentificacion(response.getTipoIdentificacion());
            validacion.setIdentificacion(response.getIdentificacion());
            validacion.setPais(response.getPais());
            validacion.setDepartamento(response.getDepartamento());
            validacion.setMunicipio(response.getMunicipio());
            validacion.setDireccion(response.getDireccion());
            return validacion;
        }
    }

    /*Save*/
    @Override
    public ErrorDTO guardarRegistro(Integer idRole){
        if(idRole==null){
            return new ErrorDTO(1,"Codigo de registro no puede ser nulo.");
        }
        validacionesRepository.save(getNewEntity(idRole));
        ErrorDTO errorDTO = new ErrorDTO(10, "Registro creado con exito");
        return errorDTO;
    }

    /*Edit*/
    @Override
    public ErrorDTO editarRegistro(ValidacionesInscripciones entidad){
        if(entidad == null){
            return new ErrorDTO(3,"Datos no pueden ser nulos.");
        }
        ValidacionesInscripciones update = validacionesRepository.findOne(entidad.getIdRole());
        if (update == null){
            return new ErrorDTO(5,"No se encontro registro");
        }else{
            validacionesRepository.saveAndFlush(entidad);
        }
        return new ErrorDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public ErrorDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new ErrorDTO(1,"Codigo de registro no puede ser nulo.");
        }
        ValidacionesInscripciones delete = validacionesRepository.findOne(codigo);
        if(delete==null){
            return new ErrorDTO(5,"No se encontro registro");
        }
        try{
            validacionesRepository.delete(codigo);
        }catch (Exception e){
            return new ErrorDTO(6,"Registro se encuentra relacionado");
        }
        return new ErrorDTO(12, "Registro eliminado con exito");
    }

    public ValidacionesInscripciones getNewEntity(Integer idRole){
        ValidacionesInscripciones entidad = new ValidacionesInscripciones();
        entidad.setIdRole(idRole);
        entidad.setPeriodo(true);
        entidad.setNombres(true);
        entidad.setApellidos(true);
        entidad.setGenero(true);
        entidad.setFechaNacimiento(true);
        entidad.setTelefonoCasa(true);
        entidad.setTelefonoCelular(true);
        entidad.setCorreoElectronico(true);
        entidad.setProfesion(true);
        entidad.setEstadoCivil(true);
        entidad.setOcupacion(true);
        entidad.setTipoIdentificacion(true);
        entidad.setIdentificacion(true);
        entidad.setPais(true);
        entidad.setDepartamento(true);
        entidad.setMunicipio(true);
        entidad.setDireccion(true);
        return entidad;
    }

}
