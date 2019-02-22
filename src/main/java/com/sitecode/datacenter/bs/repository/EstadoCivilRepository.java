package com.sitecode.datacenter.bs.repository;

import com.sitecode.datacenter.bo.EstadoCivil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 22/08/18
 */

public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer>, JpaSpecificationExecutor<Integer>{

    Iterable<EstadoCivil> findAllByOrderByDescripcionAsc();

    Page<EstadoCivil> findAllByOrderByDescripcionAsc(Pageable pageable);

}
