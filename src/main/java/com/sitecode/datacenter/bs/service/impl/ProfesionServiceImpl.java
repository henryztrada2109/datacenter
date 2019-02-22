package com.sitecode.datacenter.bs.service.impl;

import com.sitecode.datacenter.bs.repository.ProfesionRepository;
import com.sitecode.datacenter.bs.service.ProfesionService;
import com.sitecode.datacenter.bo.Profesion;
import com.sitecode.datadto.datacenter.ProfesionDTO;
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
public class ProfesionServiceImpl implements ProfesionService {

    @Autowired

    private final ProfesionRepository profesionRepository;

    @Autowired
    public ProfesionServiceImpl(ProfesionRepository profesionRepository){
        Assert.notNull(profesionRepository,"ProfesionRepository no puede ser nulo.");
        this.profesionRepository=profesionRepository;
    }

    /*List*/
    @Override
    public Page<ProfesionDTO> listAll(Pageable page){
        List<ProfesionDTO> lista = new ArrayList<>();
        Page<Profesion> listaIterable = profesionRepository.findAllByOrderByDescripcionAsc(page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                ProfesionDTO profesionDTO = new ProfesionDTO();
                profesionDTO.setDescripcion(cycle.getDescripcion());
                profesionDTO.setIdProfesion(cycle.getIdProfesion());
                lista.add(profesionDTO);
            });
        }
        Page<ProfesionDTO> myObjectsPage = new PageImpl<ProfesionDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Page<ProfesionDTO> listFilter(String descripcion, Pageable page){
        List<ProfesionDTO> lista = new ArrayList<>();
        Page<Profesion> listaIterable = profesionRepository.findByLikeDescripcion(descripcion, page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                ProfesionDTO profesionDTO = new ProfesionDTO();
                profesionDTO.setDescripcion(cycle.getDescripcion());
                profesionDTO.setIdProfesion(cycle.getIdProfesion());
                lista.add(profesionDTO);
            });
        }
        Page<ProfesionDTO> myObjectsPage = new PageImpl<ProfesionDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Iterable<ProfesionDTO> listAll(){
        List<ProfesionDTO> lista = new ArrayList<>();
        Iterable<Profesion> listaIterable = profesionRepository.findAllByOrderByDescripcionAsc();
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                ProfesionDTO profesionDTO = new ProfesionDTO();
                profesionDTO.setDescripcion(cycle.getDescripcion());
                profesionDTO.setIdProfesion(cycle.getIdProfesion());
                lista.add(profesionDTO);
            });
        }
        return lista;
    }

    /*Get*/
    @Override
    public ProfesionDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new ProfesionDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Profesion response = profesionRepository.findOne(codigo);
        if(response == null){
            return new ProfesionDTO(2,"No se encontro registro.");
        }else{
            ProfesionDTO profesionDTO = new ProfesionDTO();
            profesionDTO.setDescripcion(response.getDescripcion());
            profesionDTO.setIdProfesion(response.getIdProfesion());
            return profesionDTO;
        }
    }

    /*Save*/
    @Override
    public ProfesionDTO guardarRegistro(Profesion entidad){
        if(entidad==null){
            return new ProfesionDTO(3,"Datos no pueden ser nulos.");
        }
        entidad.setIdProfesion(null);
        Profesion profesion = profesionRepository.save(entidad);
        ProfesionDTO response = new ProfesionDTO(10, "Registro creado con exito");
        response.setIdProfesion(profesion.getIdProfesion());
        return response;
    }

    /*Edit*/
    @Override
    public ProfesionDTO editarRegistro(Profesion entidad){
        if(entidad == null){
            return new ProfesionDTO(3,"Datos no pueden ser nulos.");
        }
        Profesion update = profesionRepository.findOne(entidad.getIdProfesion());
        if (update == null){
            return new ProfesionDTO(5,"No se encontro registro");
        }else{
            profesionRepository.saveAndFlush(entidad);
        }
        return new ProfesionDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public ProfesionDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new ProfesionDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Profesion delete = profesionRepository.findOne(codigo);
        if(delete==null){
            return new ProfesionDTO(5,"No se encontro registro");
        }
        try{
            profesionRepository.delete(codigo);
        }catch (Exception e){
            return new ProfesionDTO(6,"Registro se encuentra relacionado");
        }
        return new ProfesionDTO(12, "Registro eliminado con exito");
    }

}
