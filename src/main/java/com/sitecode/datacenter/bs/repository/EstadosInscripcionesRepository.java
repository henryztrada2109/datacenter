package com.sitecode.datacenter.bs.repository;

import com.sitecode.datacenter.bo.EstadosInscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 10/07/18
 */

public interface EstadosInscripcionesRepository extends JpaRepository<EstadosInscripciones, Integer>, JpaSpecificationExecutor<Integer>{

    Iterable<EstadosInscripciones> findAllByOrderByCodigoAsc();

}
