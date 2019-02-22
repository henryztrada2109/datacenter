package com.sitecode.datacenter.bs.service;

import com.sitecode.datacenter.bo.Profesion;
import com.sitecode.datadto.datacenter.ProfesionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 17/08/18
 */

public interface ProfesionService {

    Page<ProfesionDTO> listAll(Pageable pageable);

    Page<ProfesionDTO> listFilter(String descripcion, Pageable pageable);

    Iterable<ProfesionDTO> listAll();

    ProfesionDTO getRegistro(Integer codigo);

    ProfesionDTO guardarRegistro(Profesion entidad);

    ProfesionDTO editarRegistro(Profesion entidad);

    ProfesionDTO eliminarRegistro(Integer code);

}
