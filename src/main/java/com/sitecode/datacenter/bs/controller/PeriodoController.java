package com.sitecode.datacenter.bs.controller;

import com.sitecode.datacenter.bs.service.PeriodoService;
import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.datacenter.PeriodoDTO;
import com.sitecode.datadto.datacenter.request.PeriodoRequest;
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
@RequestMapping(value = "/periodo", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad Periodo.", description = "Utiliza el archivo: Periodo")
public class PeriodoController {

    private final PeriodoService periodoService;

    @Autowired
    public PeriodoController(PeriodoService periodoService){
        Assert.notNull(periodoService,"PeriodoService no puede ser nulo");
        this.periodoService=periodoService;
    }

    @GetMapping(value = "/consultaGeneral")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro con pagineo, del archivo: Periodo" )
    public ResponseEntity<Page<PeriodoDTO>> list(Pageable page){
        Page<PeriodoDTO> response = null;
        try {
            response = periodoService.listAll(page);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaSelect")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Periodo" )
    public ResponseEntity<Iterable<PeriodoDTO>> list(){
        Iterable<PeriodoDTO> response = null;
        try {
            response = periodoService.listAll();
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear")
    @ApiOperation(value = "Crea registro", notes = "Graba un registro del archivo Periodo, recibo un Json de Periodo")
    public ResponseEntity<PeriodoDTO> create(@RequestBody PeriodoRequest entidad) throws  ServiceException{
        PeriodoDTO response = periodoService.guardarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo Periodo, recibo un Json de Periodo")
    public ResponseEntity<PeriodoDTO> edit(@RequestBody PeriodoRequest entidad) throws  ServiceException{
        PeriodoDTO response = periodoService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/eliminar/{idPeriodo}")
    @ApiOperation(value = "Elimar un registro", notes = "Elimina un registro del archivo Periodo, recibo el codigo a eliminar")
    public ResponseEntity<PeriodoDTO> eliminar(@PathVariable("idPeriodo") Integer periodo) throws  ServiceException{
        PeriodoDTO response = periodoService.eliminarRegistro(periodo);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo Periodo")
    public ResponseEntity<PeriodoDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        PeriodoDTO response = periodoService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "/actualizaPeriodoActual/{idPeriodo}")
    @ApiOperation(value = "Edita el periodo", notes = "Edita los registros del archivo Periodo, recibo el id del periodo a poner como principal")
    public ResponseEntity<ErrorDTO> updatePeriodoActual(@PathVariable("idPeriodo") Integer idPeriodo) throws  ServiceException{
        ErrorDTO response = periodoService.periodoActual(idPeriodo);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
