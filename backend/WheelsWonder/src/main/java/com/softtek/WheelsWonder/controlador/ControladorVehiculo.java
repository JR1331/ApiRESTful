package com.softtek.WheelsWonder.controlador;


import com.softtek.WheelsWonder.dto.UsuarioDTO;
import com.softtek.WheelsWonder.dto.VehiculoDTO;
import com.softtek.WheelsWonder.excepciones.UsuarioNoEncontrado;
import com.softtek.WheelsWonder.excepciones.VehiculoNoEncontrado;
import com.softtek.WheelsWonder.modelo.Tipo;
import com.softtek.WheelsWonder.modelo.Vehiculo;
import com.softtek.WheelsWonder.servicios.IVehiculoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorVehiculo {

    @Autowired
    private IVehiculoServicio servicio;

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> consultarTodos(){
        List<Vehiculo> vehiculosBBDD = servicio.consultarTodos();
        List<VehiculoDTO> vehiculoDTO = new ArrayList<>();
        for (Vehiculo elemento:
                vehiculosBBDD) {
            VehiculoDTO vDTO = new VehiculoDTO();
            vehiculoDTO.add(vDTO.castVehiculoADto(elemento));
        }
        return new ResponseEntity<>(vehiculoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VehiculoDTO> crear(@Valid @RequestBody VehiculoDTO v) {
        Vehiculo v1 = v.castVehiculo();
        v1 = servicio.crear(v1);
        return new ResponseEntity<>(v.castVehiculoADto(v1), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> consultarUno(@PathVariable(name="id") int id){
        Vehiculo v1 = servicio.consultaUno(id);
        if(v1 == null) {
            throw new VehiculoNoEncontrado("Vehiculo no encontrado " + id);
        }
        return new ResponseEntity<>((new VehiculoDTO()).castVehiculoADto(v1), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<VehiculoDTO> modificar(@Valid @RequestBody VehiculoDTO v) {
        Vehiculo v1 = servicio.consultaUno(v.getId());
        if (v1 == null) {
            throw new VehiculoNoEncontrado("Vehiculo no encontrado " + v.getId());
        }
        v1 = servicio.modificar(v.castVehiculo());
        return new ResponseEntity<>(v.castVehiculoADto(v1), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name="id") int id) {
        Vehiculo v1 = servicio.consultaUno(id);
        if (v1 == null) {
            throw new VehiculoNoEncontrado("Vehiculo no encontrado " + id);
        }
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/portipo")
    public ResponseEntity<List<Vehiculo>>consultarPorTipo( @RequestParam(name="tipo") Tipo TIPO) throws Exception{
        return new ResponseEntity<>(servicio.findByTipo(TIPO),HttpStatus.OK);
    }

    @GetMapping("/pormarca")
    public ResponseEntity<List<Vehiculo>>findByMarca( @RequestParam(name="marca") String marca) throws Exception{
        return new ResponseEntity<>(servicio.findByMarca(marca),HttpStatus.OK);
    }

    @GetMapping("/porlocalizacion")
    public ResponseEntity<List<Vehiculo>>findByLocalizacion( @RequestParam(name="localizacion") String localizacion) throws Exception{
        return new ResponseEntity<>(servicio.findByLocalizacion(localizacion),HttpStatus.OK);
    }

    @GetMapping("/porusuario")
    public ResponseEntity<List<Vehiculo>>consultarPorUsuario(@RequestParam(name="usuario") Integer id_usuario) throws Exception{
        return new ResponseEntity<>(servicio.findByUsuarioVehiculo_Id(id_usuario),HttpStatus.OK);
    }

    @GetMapping("/porprecio")
    public ResponseEntity<List<Vehiculo>> consultarPorPrecio(@RequestParam(name = "min") double precioMin,
                                                             @RequestParam(name = "max") double precioMax) {
        return new ResponseEntity<>(servicio.consultaPorPrecio(precioMin, precioMax), HttpStatus.OK);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Vehiculo>> consultarDisponibles() {
        LocalDate fechaActual = LocalDate.now();
        return new ResponseEntity<>(servicio.findVehiculosDisponibles(fechaActual), HttpStatus.OK);
    }
}
