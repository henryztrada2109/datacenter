package com.sitecode.datacenter.bs.controller;

import com.sitecode.datacenter.bs.service.EstadoCivilService;
import com.sitecode.datacenter.bo.EstadoCivil;
import com.sitecode.datadto.datacenter.EstadoCivilDTO;
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
@RequestMapping(value = "/estadoCivil", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad EstadoCivil.", description = "Utiliza el archivo: EstadoCivil")
public class EstadoCivilController {

    private final EstadoCivilService estadoCivilService;

    @Autowired
    public EstadoCivilController(EstadoCivilService estadoCivilService){
        Assert.notNull(estadoCivilService,"EstadoCivilService no puede ser nulo");
        this.estadoCivilService=estadoCivilService;
    }

    @GetMapping(value = "/consultaGeneral")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro con pagineo, del archivo: EstadoCivil" )
    public ResponseEntity<Page<EstadoCivilDTO>> list(Pageable page){
        Page<EstadoCivilDTO> response = null;
        try {
            response = estadoCivilService.listAll(page);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaSelect")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: EstadoCivil" )
    public ResponseEntity<Iterable<EstadoCivilDTO>> list(){
        Iterable<EstadoCivilDTO> response = null;
        try {
            response = estadoCivilService.listAll();
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear")
    @ApiOperation(value = "Crea registro", notes = "Graba un registro del archivo EstadoCivil, recibo un Json de EstadoCivil")
    public ResponseEntity<EstadoCivilDTO> create(@RequestBody EstadoCivil entidad) throws  ServiceException{
        EstadoCivilDTO response = estadoCivilService.guardarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo EstadoCivil, recibo un Json de EstadoCivil")
    public ResponseEntity<EstadoCivilDTO> edit(@RequestBody EstadoCivil entidad) throws  ServiceException{
        EstadoCivilDTO response = estadoCivilService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    @ApiOperation(value = "Elimar un registro", notes = "Elimina un registro del archivo EstadoCivil, recibo el codigo a eliminar")
    public ResponseEntity<EstadoCivilDTO> eliminar(@PathVariable("id") Integer id) throws  ServiceException{
        EstadoCivilDTO response = estadoCivilService.eliminarRegistro(id);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo Profesion")
    public ResponseEntity<EstadoCivilDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        EstadoCivilDTO response = estadoCivilService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
