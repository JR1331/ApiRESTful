package com.softtek.WheelsWonder.controlador;



import com.softtek.WheelsWonder.dto.UsuarioDTO;
import com.softtek.WheelsWonder.dto.ValoracionDTO;
import com.softtek.WheelsWonder.excepciones.UsuarioNoEncontrado;
import com.softtek.WheelsWonder.excepciones.ValoracionNoEncontrada;
import com.softtek.WheelsWonder.modelo.Usuario;
import com.softtek.WheelsWonder.modelo.Valoracion;
import com.softtek.WheelsWonder.servicios.IValoracionServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/valoraciones")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorValoracion {

    @Autowired
    private IValoracionServicio servicio;

    @GetMapping
    public ResponseEntity<List<ValoracionDTO>> consultarTodos(){
        List<Valoracion> valoracionesBBDD = servicio.consultarTodos();
        List<ValoracionDTO> valoracionDTO = new ArrayList<>();
        for (Valoracion elemento:
                valoracionesBBDD) {
            ValoracionDTO vDTO = new ValoracionDTO();
            valoracionDTO.add(vDTO.castValoracionADto(elemento));
        }
        return new ResponseEntity<>(valoracionDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ValoracionDTO> crear(@Valid @RequestBody ValoracionDTO v) {
        Valoracion v1 = v.castValoracion();
        v1 = servicio.crear(v1);
        return new ResponseEntity<>(v.castValoracionADto(v1), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValoracionDTO> consultarUno(@PathVariable(name="id") int id){
        Valoracion v1 = servicio.consultaUno(id);
        if(v1 == null) {
            throw new ValoracionNoEncontrada("Valoracion no encontrado " + id);
        }
        return new ResponseEntity<>((new ValoracionDTO()).castValoracionADto(v1), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<ValoracionDTO> modificar(@Valid @RequestBody ValoracionDTO v) {
        Valoracion v1 = servicio.consultaUno(v.getId());
        if (v1 == null) {
            throw new ValoracionNoEncontrada("Valoracion no encontrado " + v.getId());
        }
        v1 = servicio.modificar(v.castValoracion());
        return new ResponseEntity<>(v.castValoracionADto(v1), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name="id") int id) {
        Valoracion v1 = servicio.consultaUno(id);
        if (v1 == null) {
            throw new ValoracionNoEncontrada("Valoracion no encontrado " + id);
        }
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/countByUsuario")
    public ResponseEntity<Long> countByUsuarioId(@RequestParam(name="usuario") Integer id_usuario) {
        long count = servicio.countByUsuarioValoracion_Id(id_usuario);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/avgByUsuario")
    public ResponseEntity<Double> findAveragePuntuacionByUsuarioValoracion_Id(@RequestParam(name="usuario") Integer id_usuario) {
        double avg = servicio.findAveragePuntuacionByUsuarioValoracion_Id(id_usuario);
        return new ResponseEntity<>(avg, HttpStatus.OK);
    }
}
