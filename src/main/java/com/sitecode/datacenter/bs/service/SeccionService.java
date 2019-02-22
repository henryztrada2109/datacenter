package com.sitecode.datacenter.bs.service;

import com.sitecode.datacenter.bo.Seccion;
import com.sitecode.datadto.datacenter.SeccionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 12/07/18
 */

public interface SeccionService {

    Page<SeccionDTO> listAll(Pageable pageable, Integer idGrado);

    Iterable<SeccionDTO> listAll(Integer idGrado);

    SeccionDTO getRegistro(Integer codigo);

    SeccionDTO guardarRegistro(Seccion entidad);

    SeccionDTO editarRegistro(Seccion entidad);

    SeccionDTO eliminarRegistro(Integer code);

}
