package com.sitecode.datacenter.bs.controller;

import com.sitecode.datacenter.bs.service.EstadosInscripcionesService;
import com.sitecode.datacenter.bo.EstadosInscripciones;
import com.sitecode.datadto.datacenter.EstadosInscripcionesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 10/07/18
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/estadosInscripciones", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad EstadosInscripciones.", description = "Utiliza el archivo: EstadosInscripciones")
public class EstadosInscripcionesController {

    private final EstadosInscripcionesService estadosInscripcionesService;

    @Autowired
    public EstadosInscripcionesController(EstadosInscripcionesService estadosInscripcionesService){
        Assert.notNull(estadosInscripcionesService,"EstadosInscripcionesService no puede ser nulo");
        this.estadosInscripcionesService=estadosInscripcionesService;
    }

    @GetMapping(value = "/consultaSelect")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: EstadosInscripciones" )
    public ResponseEntity<Iterable<EstadosInscripcionesDTO>> list(){
        Iterable<EstadosInscripcionesDTO> response = null;
        try {
            response = estadosInscripcionesService.listAll();
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo EstadosInscripciones, recibo un Json de EstadosInscripciones")
    public ResponseEntity<EstadosInscripcionesDTO> edit(@RequestBody EstadosInscripciones entidad) throws  ServiceException{
        EstadosInscripcionesDTO response = estadosInscripcionesService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo EstadosInscripciones")
    public ResponseEntity<EstadosInscripcionesDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        EstadosInscripcionesDTO response = estadosInscripcionesService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
