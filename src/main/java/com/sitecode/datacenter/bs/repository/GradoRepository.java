package com.sitecode.datacenter.bs.repository;

import com.sitecode.datacenter.bo.Grado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 11/07/18
 */

public interface GradoRepository extends JpaRepository<Grado, Integer>, JpaSpecificationExecutor<Integer>{

    Iterable<Grado> findAllByOrderByOrdenAsc();

    Page<Grado> findAllByOrderByOrdenAsc(Pageable pageable);

    Iterable<Grado> findAllByIdRole(Integer idRole);

}
