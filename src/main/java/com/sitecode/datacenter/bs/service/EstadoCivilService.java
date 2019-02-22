package com.sitecode.datacenter.bs.service;

import com.sitecode.datacenter.bo.EstadoCivil;
import com.sitecode.datadto.datacenter.EstadoCivilDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 22/08/18
 */

public interface EstadoCivilService {

    Page<EstadoCivilDTO> listAll(Pageable pageable);

    Iterable<EstadoCivilDTO> listAll();

    EstadoCivilDTO getRegistro(Integer codigo);

    EstadoCivilDTO guardarRegistro(EstadoCivil entidad);

    EstadoCivilDTO editarRegistro(EstadoCivil entidad);

    EstadoCivilDTO eliminarRegistro(Integer code);

}
