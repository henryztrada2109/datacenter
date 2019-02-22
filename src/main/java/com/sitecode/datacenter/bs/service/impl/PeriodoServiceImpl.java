package com.sitecode.datacenter.bs.service.impl;

import com.sitecode.datacenter.bs.repository.PeriodoRepository;
import com.sitecode.datacenter.bs.service.PeriodoService;
import com.sitecode.datacenter.bo.Periodo;
import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.datacenter.PeriodoDTO;
import com.sitecode.datadto.datacenter.request.PeriodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 22/06/18
 */

@Service
public class PeriodoServiceImpl implements PeriodoService {

    @Autowired

    private final PeriodoRepository periodoRepository;

    @Autowired
    public PeriodoServiceImpl(PeriodoRepository periodoRepository){
        Assert.notNull(periodoRepository,"PeriodoRepository no puede ser nulo.");
        this.periodoRepository=periodoRepository;
    }

    /*List*/
    @Override
    public Page<PeriodoDTO> listAll(Pageable page){
        List<PeriodoDTO> lista = new ArrayList<>();
        Page<Periodo> listaIterable = periodoRepository.findAllByOrderByIdPeriodoDesc(page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                PeriodoDTO periodoDTO = new PeriodoDTO();
                Calendar fechaInicio = Calendar.getInstance();
                fechaInicio.setTime(cycle.getFechaInicial());
                periodoDTO.setAnioInicio(fechaInicio.get(Calendar.YEAR));
                periodoDTO.setMesInicio(fechaInicio.get(Calendar.MONTH));
                periodoDTO.setDiaInicio(fechaInicio.get(Calendar.DAY_OF_MONTH));
                Calendar fechaFin = Calendar.getInstance();
                fechaFin.setTime(cycle.getFechaFinal());
                periodoDTO.setAnioFin(fechaFin.get(Calendar.YEAR));
                periodoDTO.setMesFin(fechaFin.get(Calendar.MONTH));
                periodoDTO.setDiaFin(fechaFin.get(Calendar.DAY_OF_MONTH));
                periodoDTO.setDescripcion(cycle.getDescripcion());
                periodoDTO.setIdPeriodo(cycle.getIdPeriodo());
                periodoDTO.setPeriodoActual(cycle.getPeriodoActual());
                lista.add(periodoDTO);
            });
        }
        Page<PeriodoDTO> myObjectsPage = new PageImpl<PeriodoDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Iterable<PeriodoDTO> listAll(){
        List<PeriodoDTO> lista = new ArrayList<>();
        Iterable<Periodo> listaIterable = periodoRepository.findAllByOrderByIdPeriodoDesc();
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                PeriodoDTO periodoDTO = new PeriodoDTO();
                Calendar fechaInicio = Calendar.getInstance();
                fechaInicio.setTime(cycle.getFechaInicial());
                periodoDTO.setAnioInicio(fechaInicio.get(Calendar.YEAR));
                periodoDTO.setMesInicio(fechaInicio.get(Calendar.MONTH));
                periodoDTO.setDiaInicio(fechaInicio.get(Calendar.DAY_OF_MONTH));
                Calendar fechaFin = Calendar.getInstance();
                fechaFin.setTime(cycle.getFechaFinal());
                periodoDTO.setAnioFin(fechaFin.get(Calendar.YEAR));
                periodoDTO.setMesFin(fechaFin.get(Calendar.MONTH));
                periodoDTO.setDiaFin(fechaFin.get(Calendar.DAY_OF_MONTH));
                periodoDTO.setDescripcion(cycle.getDescripcion());
                periodoDTO.setIdPeriodo(cycle.getIdPeriodo());
                periodoDTO.setPeriodoActual(cycle.getPeriodoActual());
                lista.add(periodoDTO);
            });
        }
        return lista;
    }

    /*Get*/
    @Override
    public PeriodoDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new PeriodoDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Periodo response = periodoRepository.findOne(codigo);
        if(response == null){
            return new PeriodoDTO(2,"No se encontro registro.");
        }else{
            PeriodoDTO periodoDTO = new PeriodoDTO();
            Calendar fechaInicio = Calendar.getInstance();
            fechaInicio.setTime(response.getFechaInicial());
            periodoDTO.setAnioInicio(fechaInicio.get(Calendar.YEAR));
            periodoDTO.setMesInicio(fechaInicio.get(Calendar.MONTH));
            periodoDTO.setDiaInicio(fechaInicio.get(Calendar.DAY_OF_MONTH));
            Calendar fechaFin = Calendar.getInstance();
            fechaFin.setTime(response.getFechaFinal());
            periodoDTO.setAnioFin(fechaFin.get(Calendar.YEAR));
            periodoDTO.setMesFin(fechaFin.get(Calendar.MONTH));
            periodoDTO.setDiaFin(fechaFin.get(Calendar.DAY_OF_MONTH));
            periodoDTO.setDescripcion(response.getDescripcion());
            periodoDTO.setIdPeriodo(response.getIdPeriodo());
            periodoDTO.setPeriodoActual(response.getPeriodoActual());
            return periodoDTO;
        }
    }

    /*Save*/
    @Override
    public PeriodoDTO guardarRegistro(PeriodoRequest entidad){
        if(entidad==null){
            return new PeriodoDTO(3,"Datos no pueden ser nulos.");
        }
        entidad.setIdPeriodo(null);
        Periodo periodo = new Periodo();
        periodo.setIdPeriodo(entidad.getIdPeriodo());
        periodo.setDescripcion(entidad.getDescripcion());
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.set(entidad.getAnioInicio(), entidad.getMesInicio(), entidad.getDiaInicio());
        periodo.setFechaInicial(fechaInicial.getTime());
        Calendar fechaFinal = Calendar.getInstance();
        fechaFinal.set(entidad.getAnioFin(), entidad.getMesFin(), entidad.getDiaFin());
        periodo.setFechaFinal(fechaFinal.getTime());
        periodo.setPeriodoActual(entidad.getPeriodoActual());
        periodo.setPeriodoActual(false);
        if (periodo.getFechaFinal().compareTo(periodo.getFechaInicial()) < 0 ) {
            return new PeriodoDTO(50,"Fecha final debe ser mayor o igual a fecha inicial.");
        }
        periodoRepository.save(periodo);
        return new PeriodoDTO(10, "Registro creado con exito");
    }

    /*Edit*/
    @Override
    public PeriodoDTO editarRegistro(PeriodoRequest entidad){
        if(entidad == null){
            return new PeriodoDTO(3,"Datos no pueden ser nulos.");
        }
        Periodo update = periodoRepository.findOne(entidad.getIdPeriodo());
        if (update == null){
            return new PeriodoDTO(5,"No se encontro registro");
        }else{
            Periodo periodo = new Periodo();
            periodo.setIdPeriodo(entidad.getIdPeriodo());
            periodo.setDescripcion(entidad.getDescripcion());
            Calendar fechaInicial = Calendar.getInstance();
            fechaInicial.set(entidad.getAnioInicio(), entidad.getMesInicio(), entidad.getDiaInicio());
            periodo.setFechaInicial(fechaInicial.getTime());
            Calendar fechaFinal = Calendar.getInstance();
            fechaFinal.set(entidad.getAnioFin(), entidad.getMesFin(), entidad.getDiaFin());
            periodo.setFechaFinal(fechaFinal.getTime());
            periodo.setPeriodoActual(entidad.getPeriodoActual());
            if (periodo.getFechaFinal().compareTo(periodo.getFechaInicial()) < 0 ) {
                return new PeriodoDTO(50,"Fecha final debe ser mayor o igual a fecha inicial.");
            }
            periodoRepository.saveAndFlush(periodo);
        }
        return new PeriodoDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public PeriodoDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new PeriodoDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Periodo delete = periodoRepository.findOne(codigo);
        if(delete==null){
            return new PeriodoDTO(5,"No se encontro registro");
        }
        try{
            periodoRepository.delete(codigo);
        }catch (Exception e){
            return new PeriodoDTO(6,"Registro se encuentra relacionado");
        }
        return new PeriodoDTO(12, "Registro eliminado con exito");
    }

    /*Editar periodo actual*/
    @Override
    public ErrorDTO periodoActual(Integer codigo){
        if(codigo == null){
            return new ErrorDTO(3,"Datos no pueden ser nulos.");
        }
        Periodo actualizar = periodoRepository.findOne(codigo);
        if (actualizar == null) {
            return new ErrorDTO(20, "No se encontro registro.");
        }
        Iterable<Periodo> iterable = periodoRepository.findAllByOrderByIdPeriodoDesc();
        iterable.forEach(cycle -> {
            cycle.setPeriodoActual(false);
            periodoRepository.saveAndFlush(cycle);
        });
        actualizar.setPeriodoActual(true);
        periodoRepository.saveAndFlush(actualizar);
        return new ErrorDTO(21, "Periodo actual modificado.");
    }

}
