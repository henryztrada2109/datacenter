package com.sitecode.datacenter.bs.repository;

import com.sitecode.datacenter.bo.Periodo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 10/07/18
 */

public interface PeriodoRepository extends JpaRepository<Periodo, Integer>, JpaSpecificationExecutor<Integer>{

    Iterable<Periodo> findAllByOrderByIdPeriodoDesc();

    Page<Periodo> findAllByOrderByIdPeriodoDesc(Pageable pageable);

}
