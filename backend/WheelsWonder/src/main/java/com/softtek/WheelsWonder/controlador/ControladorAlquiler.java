package com.softtek.WheelsWonder.controlador;

import com.softtek.WheelsWonder.dto.AlquilerDTO;
import com.softtek.WheelsWonder.dto.UsuarioDTO;
import com.softtek.WheelsWonder.excepciones.AlquilerNoEncontrado;
import com.softtek.WheelsWonder.excepciones.UsuarioNoEncontrado;
import com.softtek.WheelsWonder.modelo.Alquiler;
import com.softtek.WheelsWonder.modelo.Tipo;
import com.softtek.WheelsWonder.modelo.Usuario;
import com.softtek.WheelsWonder.modelo.Vehiculo;
import com.softtek.WheelsWonder.servicios.IAlquilerServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alquileres")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorAlquiler {
    @Autowired
    private IAlquilerServicio servicio;

    @GetMapping
    public ResponseEntity<List<AlquilerDTO>> consultarTodos(){
        List<Alquiler> alquileresBBDD = servicio.consultarTodos();
        List<AlquilerDTO> alquilerDTO = new ArrayList<>();
        for (Alquiler elemento:
                alquileresBBDD) {
            AlquilerDTO aDTO = new AlquilerDTO();
            alquilerDTO.add(aDTO.castAlquilerADto(elemento));
        }
        return new ResponseEntity<>(alquilerDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlquilerDTO> crear(@Valid @RequestBody AlquilerDTO a) {
        Alquiler a1 = a.castAlquiler();
        a1 = servicio.crear(a1);
        return new ResponseEntity<>(a.castAlquilerADto(a1), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerDTO> consultarUno(@PathVariable(name="id") int id){
        Alquiler a1 = servicio.consultaUno(id);
        if(a1 == null) {
            throw new AlquilerNoEncontrado("Alquiler no encontrado " + id);
        }
        return new ResponseEntity<>((new AlquilerDTO()).castAlquilerADto(a1), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<AlquilerDTO> modificar(@Valid @RequestBody AlquilerDTO a) {
        Alquiler a1 = servicio.consultaUno(a.getId());
        if (a1 == null) {
            throw new AlquilerNoEncontrado("Alquiler no encontrado " + a.getId());
        }
        a1 = servicio.modificar(a.castAlquiler());
        return new ResponseEntity<>(a.castAlquilerADto(a1), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name="id") int id) {
        Alquiler a1 = servicio.consultaUno(id);
        if (a1 == null) {
            throw new AlquilerNoEncontrado("Alquiler no encontrado " + id);
        }
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
