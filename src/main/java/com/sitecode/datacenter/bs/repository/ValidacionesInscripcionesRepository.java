package com.sitecode.datacenter.bs.repository;

import com.sitecode.datacenter.bo.ValidacionesInscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 11/07/18
 */

public interface ValidacionesInscripcionesRepository extends JpaRepository<ValidacionesInscripciones, Integer>,
        JpaSpecificationExecutor<Integer>{

}
