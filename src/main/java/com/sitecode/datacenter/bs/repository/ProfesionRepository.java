package com.sitecode.datacenter.bs.repository;

import com.sitecode.datacenter.bo.Profesion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 17/08/18
 */

public interface ProfesionRepository extends JpaRepository<Profesion, Integer>, JpaSpecificationExecutor<Integer>{

    Iterable<Profesion> findAllByOrderByDescripcionAsc();

    Page<Profesion> findAllByOrderByDescripcionAsc(Pageable pageable);

    @Query("FROM Profesion p WHERE p.descripcion LIKE CONCAT('%',:descripcion,'%')")
    Page<Profesion> findByLikeDescripcion(@Param("descripcion") String descripcion, Pageable pageable);

}
