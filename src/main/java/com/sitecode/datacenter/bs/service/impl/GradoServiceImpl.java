package com.sitecode.datacenter.bs.service.impl;

import com.sitecode.datacenter.bs.repository.GradoRepository;
import com.sitecode.datacenter.bs.repository.SeccionRepository;
import com.sitecode.datacenter.bs.service.GradoService;
import com.sitecode.datacenter.bo.Grado;
import com.sitecode.datacenter.bo.Seccion;
import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.datacenter.GradoDTO;
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
 * created on 22/06/18
 */

@Service
public class GradoServiceImpl implements GradoService {

    @Autowired

    private final GradoRepository gradoRepository;
    private final SeccionRepository seccionRepository;

    @Autowired
    public GradoServiceImpl(GradoRepository gradoRepository,
                            SeccionRepository seccionRepository){
        Assert.notNull(gradoRepository,"GradoRepository no puede ser nulo.");
        Assert.notNull(seccionRepository,"SeccionRepository no puede ser nulo.");
        this.gradoRepository=gradoRepository;
        this.seccionRepository=seccionRepository;
    }

    /*List*/
    @Override
    public Page<GradoDTO> listAll(Pageable page){
        List<GradoDTO> lista = new ArrayList<>();
        Page<Grado> listaIterable = gradoRepository.findAllByOrderByOrdenAsc(page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                List<Seccion> listSecciones = new ArrayList<>();
                seccionRepository.findAllByIdGradoOrderByOrdenAsc(cycle.getIdGrado()).forEach(cycleSeccion -> {
                    listSecciones.add(cycleSeccion);
                });
                GradoDTO gradoDTO = new GradoDTO();
                gradoDTO.setCantidadCuotas(cycle.getCantidadCuotas());
                gradoDTO.setCantidadSecciones(listSecciones.size());
                gradoDTO.setCostoCuota(cycle.getCostoCuota());
                gradoDTO.setCostoInscripcion(cycle.getCostoInscripcion());
                gradoDTO.setIdGrado(cycle.getIdGrado());
                gradoDTO.setIdRole(cycle.getIdRole());
                gradoDTO.setIdTipoMenu(cycle.getIdTipoMenu());
                gradoDTO.setNombre(cycle.getNombre());
                gradoDTO.setOrden(cycle.getOrden());
                lista.add(gradoDTO);
            });
        }
        Page<GradoDTO> myObjectsPage = new PageImpl<GradoDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Iterable<GradoDTO> listAll(){
        List<GradoDTO> lista = new ArrayList<>();
        Iterable<Grado> listaIterable = gradoRepository.findAllByOrderByOrdenAsc();
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                GradoDTO gradoDTO = new GradoDTO();
                gradoDTO.setCantidadCuotas(cycle.getCantidadCuotas());
                gradoDTO.setCostoCuota(cycle.getCostoCuota());
                gradoDTO.setCostoInscripcion(cycle.getCostoInscripcion());
                gradoDTO.setIdGrado(cycle.getIdGrado());
                gradoDTO.setIdRole(cycle.getIdRole());
                gradoDTO.setIdTipoMenu(cycle.getIdTipoMenu());
                gradoDTO.setNombre(cycle.getNombre());
                gradoDTO.setOrden(cycle.getOrden());
                lista.add(gradoDTO);
            });
        }
        return lista;
    }

    /*Get*/
    @Override
    public GradoDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new GradoDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Grado response = gradoRepository.findOne(codigo);
        if(response == null){
            return new GradoDTO(2,"No se encontro registro.");
        }else{
            GradoDTO gradoDTO = new GradoDTO();
            gradoDTO.setCantidadCuotas(response.getCantidadCuotas());
            gradoDTO.setCostoCuota(response.getCostoCuota());
            gradoDTO.setCostoInscripcion(response.getCostoInscripcion());
            gradoDTO.setIdGrado(response.getIdGrado());
            gradoDTO.setIdRole(response.getIdRole());
            gradoDTO.setIdTipoMenu(response.getIdTipoMenu());
            gradoDTO.setNombre(response.getNombre());
            gradoDTO.setOrden(response.getOrden());
            return gradoDTO;
        }
    }

    /*Save*/
    @Override
    public GradoDTO guardarRegistro(Grado entidad){
        if(entidad==null){
            return new GradoDTO(3,"Datos no pueden ser nulos.");
        }
        entidad.setIdGrado(null);
        Grado grado = gradoRepository.save(entidad);
        GradoDTO gradoDTO = new GradoDTO(10, "Registro creado con exito");
        gradoDTO.setIdGrado(grado.getIdGrado());
        return gradoDTO;
    }

    /*Edit*/
    @Override
    public GradoDTO editarRegistro(Grado entidad){
        if(entidad == null){
            return new GradoDTO(3,"Datos no pueden ser nulos.");
        }
        Grado update = gradoRepository.findOne(entidad.getIdGrado());
        if (update == null){
            return new GradoDTO(5,"No se encontro registro");
        }else{
            gradoRepository.saveAndFlush(entidad);
        }
        return new GradoDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public GradoDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new GradoDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Grado delete = gradoRepository.findOne(codigo);
        if(delete==null){
            return new GradoDTO(5,"No se encontro registro");
        }
        try{
            gradoRepository.delete(codigo);
        }catch (Exception e){
            return new GradoDTO(6,"Registro se encuentra relacionado");
        }
        return new GradoDTO(12, "Registro eliminado con exito");
    }

    /*Get relacion*/
    @Override
    public ErrorDTO getValidacion(Integer idRole){
        List<Integer> listResponse = new ArrayList<>();
        if(idRole==null){
            return new GradoDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Iterable<Grado> response = gradoRepository.findAllByIdRole(idRole);
        response.forEach( cycle -> {
            listResponse.add(1);
        });
        if(listResponse.size() == 0){
            return new GradoDTO(90,"No se encontro registro.");
        }else{
            return new GradoDTO(91,"Role se encuentra relacionado.");
        }
    }

}
