package com.sitecode.datacenter.bs.service;

import com.sitecode.datacenter.bo.Grado;
import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.datacenter.GradoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 11/07/18
 */

public interface GradoService {

    Page<GradoDTO> listAll(Pageable pageable);

    Iterable<GradoDTO> listAll();

    GradoDTO getRegistro(Integer codigo);

    GradoDTO guardarRegistro(Grado entidad);

    GradoDTO editarRegistro(Grado entidad);

    GradoDTO eliminarRegistro(Integer code);

    ErrorDTO getValidacion(Integer idRole);

}
