package com.sitecode.datacenter.bs.controller;

import com.sitecode.datacenter.bs.service.SeccionService;
import com.sitecode.datacenter.bo.Seccion;
import com.sitecode.datadto.datacenter.SeccionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * created on 12/07/18
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/seccion", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad Seccion.", description = "Utiliza el archivo: Seccion")
public class SeccionController {

    private final SeccionService seccionService;

    @Autowired
    public SeccionController(SeccionService seccionService){
        Assert.notNull(seccionService,"SeccionService no puede ser nulo");
        this.seccionService=seccionService;
    }

    @GetMapping(value = "/consultaGeneral/{idGrado}")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro con pagineo, del archivo: Seccion" )
    public ResponseEntity<Page<SeccionDTO>> list(@PathVariable("idGrado") Integer idGrado,
                                                 Pageable page){
        Page<SeccionDTO> response = null;
        try {
            response = seccionService.listAll(page, idGrado);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaSelect/{idGrado}")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Seccion" )
    public ResponseEntity<Iterable<SeccionDTO>> list(@PathVariable("idGrado") Integer idGrado){
        Iterable<SeccionDTO> response = null;
        try {
            response = seccionService.listAll(idGrado);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear")
    @ApiOperation(value = "Crea registro", notes = "Graba un registro del archivo Seccion, recibo un Json de Seccion")
    public ResponseEntity<SeccionDTO> create(@RequestBody Seccion entidad) throws  ServiceException{
        SeccionDTO response = seccionService.guardarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo Seccion, recibo un Json de Seccion")
    public ResponseEntity<SeccionDTO> edit(@RequestBody Seccion entidad) throws  ServiceException{
        SeccionDTO response = seccionService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    @ApiOperation(value = "Elimar un registro", notes = "Elimina un registro del archivo Seccion, recibo el codigo a eliminar")
    public ResponseEntity<SeccionDTO> eliminar(@PathVariable("id") Integer id) throws  ServiceException{
        SeccionDTO response = seccionService.eliminarRegistro(id);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo Seccion")
    public ResponseEntity<SeccionDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        SeccionDTO response = seccionService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
