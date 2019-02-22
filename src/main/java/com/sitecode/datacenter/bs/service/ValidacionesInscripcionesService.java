package com.sitecode.datacenter.bs.service;

import com.sitecode.datacenter.bo.ValidacionesInscripciones;
import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.datacenter.ValidacionesInscripcionesDTO;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 11/07/18
 */

public interface ValidacionesInscripcionesService {

    ValidacionesInscripcionesDTO getRegistro(Integer codigo);

    ErrorDTO guardarRegistro(Integer idRole);

    ErrorDTO editarRegistro(ValidacionesInscripciones entidad);

    ErrorDTO eliminarRegistro(Integer codigo);

}
