package com.sitecode.datacenter.bs.service.impl;

import com.sitecode.datacenter.bs.repository.EstadoCivilRepository;
import com.sitecode.datacenter.bs.service.EstadoCivilService;
import com.sitecode.datacenter.bo.EstadoCivil;
import com.sitecode.datadto.datacenter.EstadoCivilDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 17/08/18
 */

@Service
public class EstadoCivilServiceImpl implements EstadoCivilService {

    @Autowired

    private final EstadoCivilRepository estadoCivilRepository;

    @Autowired
    public EstadoCivilServiceImpl(EstadoCivilRepository estadoCivilRepository){
        Assert.notNull(estadoCivilRepository,"EstadoCivilRepository no puede ser nulo.");
        this.estadoCivilRepository=estadoCivilRepository;
    }

    /*List*/
    @Override
    public Page<EstadoCivilDTO> listAll(Pageable page){
        List<EstadoCivilDTO> lista = new ArrayList<>();
        Page<EstadoCivil> listaIterable = estadoCivilRepository.findAllByOrderByDescripcionAsc(page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                EstadoCivilDTO estadoCivilDTO = new EstadoCivilDTO();
                estadoCivilDTO.setDescripcion(cycle.getDescripcion());
                estadoCivilDTO.setIdEstado(cycle.getIdEstado());
                lista.add(estadoCivilDTO);
            });
        }
        Page<EstadoCivilDTO> myObjectsPage = new PageImpl<EstadoCivilDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Iterable<EstadoCivilDTO> listAll(){
        List<EstadoCivilDTO> lista = new ArrayList<>();
        Iterable<EstadoCivil> listaIterable = estadoCivilRepository.findAllByOrderByDescripcionAsc();
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                EstadoCivilDTO estadoCivilDTO = new EstadoCivilDTO();
                estadoCivilDTO.setDescripcion(cycle.getDescripcion());
                estadoCivilDTO.setIdEstado(cycle.getIdEstado());
                lista.add(estadoCivilDTO);
            });
        }
        return lista;
    }

    /*Get*/
    @Override
    public EstadoCivilDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new EstadoCivilDTO(1,"Codigo de registro no puede ser nulo.");
        }
        EstadoCivil response = estadoCivilRepository.findOne(codigo);
        if(response == null){
            return new EstadoCivilDTO(2,"No se encontro registro.");
        }else{
            EstadoCivilDTO estadoCivilDTO = new EstadoCivilDTO();
            estadoCivilDTO.setDescripcion(response.getDescripcion());
            estadoCivilDTO.setIdEstado(response.getIdEstado());
            return estadoCivilDTO;
        }
    }

    /*Save*/
    @Override
    public EstadoCivilDTO guardarRegistro(EstadoCivil entidad){
        if(entidad==null){
            return new EstadoCivilDTO(3,"Datos no pueden ser nulos.");
        }
        entidad.setIdEstado(null);
        EstadoCivil estadoCivil = estadoCivilRepository.save(entidad);
        EstadoCivilDTO response = new EstadoCivilDTO(10, "Registro creado con exito");
        response.setIdEstado(estadoCivil.getIdEstado());
        return response;
    }

    /*Edit*/
    @Override
    public EstadoCivilDTO editarRegistro(EstadoCivil entidad){
        if(entidad == null){
            return new EstadoCivilDTO(3,"Datos no pueden ser nulos.");
        }
        EstadoCivil update = estadoCivilRepository.findOne(entidad.getIdEstado());
        if (update == null){
            return new EstadoCivilDTO(5,"No se encontro registro");
        }else{
            estadoCivilRepository.saveAndFlush(entidad);
        }
        return new EstadoCivilDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public EstadoCivilDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new EstadoCivilDTO(1,"Codigo de registro no puede ser nulo.");
        }
        EstadoCivil delete = estadoCivilRepository.findOne(codigo);
        if(delete==null){
            return new EstadoCivilDTO(5,"No se encontro registro");
        }
        try{
            estadoCivilRepository.delete(codigo);
        }catch (Exception e){
            return new EstadoCivilDTO(6,"Registro se encuentra relacionado");
        }
        return new EstadoCivilDTO(12, "Registro eliminado con exito");
    }

}
