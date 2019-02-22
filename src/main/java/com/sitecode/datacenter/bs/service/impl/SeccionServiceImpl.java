package com.sitecode.datacenter.bs.service.impl;

import com.sitecode.datacenter.bs.repository.GradoRepository;
import com.sitecode.datacenter.bs.repository.SeccionRepository;
import com.sitecode.datacenter.bs.service.SeccionService;
import com.sitecode.datacenter.bo.Grado;
import com.sitecode.datacenter.bo.Seccion;
import com.sitecode.datadto.datacenter.SeccionDTO;
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
 * created on 12/07/18
 */

@Service
public class SeccionServiceImpl implements SeccionService {

    @Autowired

    private final SeccionRepository seccionRepository;
    private final GradoRepository gradoRepository;

    @Autowired
    public SeccionServiceImpl(SeccionRepository seccionRepository,
                              GradoRepository gradoRepository){
        Assert.notNull(seccionRepository,"SeccionRepository no puede ser nulo.");
        Assert.notNull(gradoRepository,"GradoRepository no puede ser nulo.");
        this.seccionRepository=seccionRepository;
        this.gradoRepository=gradoRepository;
    }

    /*List*/
    @Override
    public Page<SeccionDTO> listAll(Pageable page, Integer idGrado){
        List<SeccionDTO> lista = new ArrayList<>();
        Page<Seccion> listaIterable = seccionRepository.findAllByIdGradoOrderByOrdenAsc(page, idGrado);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                SeccionDTO seccionDTO = new SeccionDTO();
                seccionDTO.setCapacidad(cycle.getCapacidad());
                seccionDTO.setIdGrado(cycle.getIdGrado());
                seccionDTO.setIdSeccion(cycle.getIdSeccion());
                seccionDTO.setNombre(cycle.getNombre());
                seccionDTO.setOrden(cycle.getOrden());
                lista.add(seccionDTO);
            });
        }
        Page<SeccionDTO> myObjectsPage = new PageImpl<SeccionDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Iterable<SeccionDTO> listAll(Integer idGrado){
        List<SeccionDTO> lista = new ArrayList<>();
        Iterable<Seccion> listaIterable = seccionRepository.findAllByIdGradoOrderByOrdenAsc(idGrado);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                SeccionDTO seccionDTO = new SeccionDTO();
                seccionDTO.setCapacidad(cycle.getCapacidad());
                seccionDTO.setIdGrado(cycle.getIdGrado());
                seccionDTO.setIdSeccion(cycle.getIdSeccion());
                seccionDTO.setNombre(cycle.getNombre());
                seccionDTO.setOrden(cycle.getOrden());
                lista.add(seccionDTO);
            });
        }
        return lista;
    }

    /*Get*/
    @Override
    public SeccionDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new SeccionDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Seccion response = seccionRepository.findOne(codigo);
        if(response == null){
            return new SeccionDTO(2,"No se encontro registro.");
        }else{
            SeccionDTO seccionDTO = new SeccionDTO();
            seccionDTO.setCapacidad(response.getCapacidad());
            seccionDTO.setIdGrado(response.getIdGrado());
            seccionDTO.setIdSeccion(response.getIdSeccion());
            seccionDTO.setNombre(response.getNombre());
            seccionDTO.setOrden(response.getOrden());
            return seccionDTO;
        }
    }

    /*Save*/
    @Override
    public SeccionDTO guardarRegistro(Seccion entidad){
        if(entidad==null){
            return new SeccionDTO(3,"Datos no pueden ser nulos.");
        }
        Grado grado = gradoRepository.findOne(entidad.getIdGrado());
        if (grado == null) {
            return new SeccionDTO(4, "Codigo de grado no existe");
        }
        entidad.setIdSeccion(null);
        Seccion seccion = seccionRepository.save(entidad);
        SeccionDTO response = new SeccionDTO(10, "Registro creado con exito");
        response.setIdSeccion(seccion.getIdSeccion());
        return response;
    }

    /*Edit*/
    @Override
    public SeccionDTO editarRegistro(Seccion entidad){
        if(entidad == null){
            return new SeccionDTO(3,"Datos no pueden ser nulos.");
        }
        Grado grado = gradoRepository.findOne(entidad.getIdGrado());
        if (grado == null) {
            return new SeccionDTO(4, "Codigo de grado no existe");
        }
        Seccion update = seccionRepository.findOne(entidad.getIdSeccion());
        if (update == null){
            return new SeccionDTO(5,"No se encontro registro");
        }else{
            seccionRepository.saveAndFlush(entidad);
        }
        return new SeccionDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public SeccionDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new SeccionDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Seccion delete = seccionRepository.findOne(codigo);
        if(delete==null){
            return new SeccionDTO(5,"No se encontro registro");
        }
        try{
            seccionRepository.delete(codigo);
        }catch (Exception e){
            return new SeccionDTO(6,"Registro se encuentra relacionado");
        }
        return new SeccionDTO(12, "Registro eliminado con exito");
    }

}
