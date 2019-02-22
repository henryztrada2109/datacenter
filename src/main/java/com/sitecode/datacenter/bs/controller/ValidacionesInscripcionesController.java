package com.sitecode.datacenter.bs.controller;

import com.sitecode.datacenter.bs.service.ValidacionesInscripcionesService;
import com.sitecode.datacenter.bo.ValidacionesInscripciones;
import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.datacenter.ValidacionesInscripcionesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/validacionesIns", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad de valiciones", description = "Utiliza el archivo: ValidacionesInscripciones")
public class ValidacionesInscripcionesController {

    private final ValidacionesInscripcionesService service;

    @Autowired
    public ValidacionesInscripcionesController(ValidacionesInscripcionesService service){
        Assert.notNull(service,"ValidacionesInscripcionesService no puede ser nulo");
        this.service=service;
    }

    @PostMapping(value = "/crear/{idRole}")
    @ApiOperation(value = "Crea registro", notes = "Graba un registro del archivo ValidacionesInscripciones, recibo el id del role a crear su registro")
    public ResponseEntity<ErrorDTO> create(@PathVariable("idRole") Integer idRole) throws  ServiceException{
        ErrorDTO response = service.guardarRegistro(idRole);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo ValidacionesInscripciones, recibo un Json de la entidad")
    public ResponseEntity<ErrorDTO> edit(@RequestBody ValidacionesInscripciones entidad) throws  ServiceException{
        ErrorDTO response = service.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo Grado")
    public ResponseEntity<ValidacionesInscripcionesDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        ValidacionesInscripcionesDTO response = service.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    @ApiOperation(value = "Elimar un registro", notes = "Elimina un registro del archivo ValidacionesInscripciones, recibo el codigo a eliminar")
    public ResponseEntity<ErrorDTO> eliminar(@PathVariable("id") Integer id) throws  ServiceException{
        ErrorDTO response = service.eliminarRegistro(id);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
