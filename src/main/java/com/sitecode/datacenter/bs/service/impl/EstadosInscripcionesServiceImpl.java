package com.sitecode.datacenter.bs.service.impl;

import com.sitecode.datacenter.bs.repository.EstadosInscripcionesRepository;
import com.sitecode.datacenter.bs.service.EstadosInscripcionesService;
import com.sitecode.datacenter.bo.EstadosInscripciones;
import com.sitecode.datadto.datacenter.EstadosInscripcionesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 10/07/18
 */

@Service
public class EstadosInscripcionesServiceImpl implements EstadosInscripcionesService {

    @Autowired

    private final EstadosInscripcionesRepository estadosInscripcionesRepository;

    @Autowired
    public EstadosInscripcionesServiceImpl(EstadosInscripcionesRepository estadosInscripcionesRepository){
        Assert.notNull(estadosInscripcionesRepository,"EstadosInscripcionesRepository no puede ser nulo.");
        this.estadosInscripcionesRepository=estadosInscripcionesRepository;
    }

    /*List*/
    @Override
    public Iterable<EstadosInscripcionesDTO> listAll(){
        List<EstadosInscripcionesDTO> lista = new ArrayList<>();
        Iterable<EstadosInscripciones> listaIterable = estadosInscripcionesRepository.findAllByOrderByCodigoAsc();
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                EstadosInscripcionesDTO dto = new EstadosInscripcionesDTO();
                dto.setCodigo(cycle.getCodigo());
                dto.setDescripcion(cycle.getDescripcion());
                dto.setIdEstado(cycle.getIdEstado());
                lista.add(dto);
            });
        }
        return lista;
    }

    /*Get*/
    @Override
    public EstadosInscripcionesDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new EstadosInscripcionesDTO(1,"Codigo de registro no puede ser nulo.");
        }
        EstadosInscripciones response = estadosInscripcionesRepository.findOne(codigo);
        if(response == null){
            return new EstadosInscripcionesDTO(2,"No se encontro registro.");
        }else{
            EstadosInscripcionesDTO dto = new EstadosInscripcionesDTO();
            dto.setCodigo(response.getCodigo());
            dto.setDescripcion(response.getDescripcion());
            dto.setIdEstado(response.getIdEstado());
            return dto;
        }
    }

    /*Edit*/
    @Override
    public EstadosInscripcionesDTO editarRegistro(EstadosInscripciones entidad){
        if(entidad.getCodigo() == null){
            return new EstadosInscripcionesDTO(1,"Codigo de registro no puede ser nulo.");
        }
        EstadosInscripciones update = estadosInscripcionesRepository.findOne(entidad.getIdEstado());
        if (update == null){
            return new EstadosInscripcionesDTO(5,"No se encontro registro");
        }else{
            estadosInscripcionesRepository.saveAndFlush(entidad);
        }
        return new EstadosInscripcionesDTO(11, "Registro modificado con exito");
    }

}
