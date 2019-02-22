package com.sitecode.datacenter.bs.service;

import com.sitecode.datacenter.bo.EstadosInscripciones;
import com.sitecode.datadto.datacenter.EstadosInscripcionesDTO;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 10/07/18
 */

public interface EstadosInscripcionesService {

    Iterable<EstadosInscripcionesDTO> listAll();

    EstadosInscripcionesDTO getRegistro(Integer codigo);

    EstadosInscripcionesDTO editarRegistro(EstadosInscripciones entidad);

}
