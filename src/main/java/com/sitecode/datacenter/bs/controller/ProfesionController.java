package com.sitecode.datacenter.bs.controller;

import com.sitecode.datacenter.bs.service.ProfesionService;
import com.sitecode.datacenter.bo.Profesion;
import com.sitecode.datadto.datacenter.ProfesionDTO;
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
@RequestMapping(value = "/profesion", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad Profesion.", description = "Utiliza el archivo: Profesion")
public class ProfesionController {

    private final ProfesionService profesionService;

    @Autowired
    public ProfesionController(ProfesionService profesionService){
        Assert.notNull(profesionService,"ProfesionService no puede ser nulo");
        this.profesionService=profesionService;
    }

    @GetMapping(value = "/consultaGeneral")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro con pagineo, del archivo: Profesion" )
    public ResponseEntity<Page<ProfesionDTO>> list(Pageable page){
        Page<ProfesionDTO> response = null;
        try {
            response = profesionService.listAll(page);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaFiltro/{descripcion}")
    @ApiOperation(value = "Lista", notes = "Retorna una lista de todos los registro, que coincidan con la cadena, del archivo: Profesion" )
    public ResponseEntity<Page<ProfesionDTO>> listFilter(@PathVariable("descripcion") String descripcion, Pageable page){
        Page<ProfesionDTO> response = null;
        try {
            response = profesionService.listFilter(descripcion, page);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaSelect")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Profesion" )
    public ResponseEntity<Iterable<ProfesionDTO>> list(){
        Iterable<ProfesionDTO> response = null;
        try {
            response = profesionService.listAll();
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear")
    @ApiOperation(value = "Crea registro", notes = "Graba un registro del archivo Profesion, recibo un Json de Profesion")
    public ResponseEntity<ProfesionDTO> create(@RequestBody Profesion entidad) throws  ServiceException{
        ProfesionDTO response = profesionService.guardarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo Profesion, recibo un Json de Profesion")
    public ResponseEntity<ProfesionDTO> edit(@RequestBody Profesion entidad) throws  ServiceException{
        ProfesionDTO response = profesionService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    @ApiOperation(value = "Elimar un registro", notes = "Elimina un registro del archivo Profesion, recibo el codigo a eliminar")
    public ResponseEntity<ProfesionDTO> eliminar(@PathVariable("id") Integer id) throws  ServiceException{
        ProfesionDTO response = profesionService.eliminarRegistro(id);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo Profesion")
    public ResponseEntity<ProfesionDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        ProfesionDTO response = profesionService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
