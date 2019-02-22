package com.sitecode.datacenter.bs.controller;

import com.sitecode.datacenter.bs.service.GradoService;
import com.sitecode.datacenter.bo.Grado;
import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.datacenter.GradoDTO;
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
 * created on 10/07/18
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/grado", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad Grado.", description = "Utiliza el archivo: Grado")
public class GradoController {

    private final GradoService gradoService;

    @Autowired
    public GradoController(GradoService gradoService){
        Assert.notNull(gradoService,"GradoService no puede ser nulo");
        this.gradoService=gradoService;
    }

    @GetMapping(value = "/consultaGeneral")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro con pagineo, del archivo: Grado" )
    public ResponseEntity<Page<GradoDTO>> list(Pageable page){
        Page<GradoDTO> response = null;
        try {
            response = gradoService.listAll(page);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaSelect")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Grado" )
    public ResponseEntity<Iterable<GradoDTO>> list(){
        Iterable<GradoDTO> response = null;
        try {
            response = gradoService.listAll();
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear")
    @ApiOperation(value = "Crea registro", notes = "Graba un registro del archivo Grado, recibo un Json de Grado")
    public ResponseEntity<GradoDTO> create(@RequestBody Grado entidad) throws  ServiceException{
        GradoDTO response = gradoService.guardarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo Grado, recibo un Json de Grado")
    public ResponseEntity<GradoDTO> edit(@RequestBody Grado entidad) throws  ServiceException{
        GradoDTO response = gradoService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/eliminar/{idGrado}")
    @ApiOperation(value = "Elimar un registro", notes = "Elimina un registro del archivo Grado, recibo el codigo a eliminar")
    public ResponseEntity<GradoDTO> eliminar(@PathVariable("idGrado") Integer id) throws  ServiceException{
        GradoDTO response = gradoService.eliminarRegistro(id);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo Grado")
    public ResponseEntity<GradoDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        GradoDTO response = gradoService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "validacion/{codigo}")
    @ApiOperation(value = "Obtiene validacion de roles", notes = "Obtiene un objeto tipo ErrorDto")
    public ResponseEntity<ErrorDTO> getValidacion(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        ErrorDTO response = gradoService.getValidacion(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
