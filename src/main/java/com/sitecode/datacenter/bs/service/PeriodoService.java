package com.sitecode.datacenter.bs.service;

import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.datacenter.PeriodoDTO;
import com.sitecode.datadto.datacenter.request.PeriodoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 10/07/18
 */

public interface PeriodoService {

    Page<PeriodoDTO> listAll(Pageable pageable);

    Iterable<PeriodoDTO> listAll();

    PeriodoDTO getRegistro(Integer codigo);

    PeriodoDTO guardarRegistro(PeriodoRequest entidad);

    PeriodoDTO editarRegistro(PeriodoRequest entidad);

    PeriodoDTO eliminarRegistro(Integer code);

    ErrorDTO periodoActual(Integer code);

}
