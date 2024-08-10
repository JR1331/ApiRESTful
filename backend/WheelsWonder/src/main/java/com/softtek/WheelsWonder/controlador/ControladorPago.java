package com.softtek.WheelsWonder.controlador;


import com.softtek.WheelsWonder.dto.PagoDTO;
import com.softtek.WheelsWonder.dto.UsuarioDTO;
import com.softtek.WheelsWonder.excepciones.PagoNoEncontrado;
import com.softtek.WheelsWonder.excepciones.UsuarioNoEncontrado;
import com.softtek.WheelsWonder.modelo.Pago;
import com.softtek.WheelsWonder.modelo.Usuario;
import com.softtek.WheelsWonder.servicios.IPagoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pagos")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorPago {

    @Autowired
    private IPagoServicio servicio;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> consultarTodos(){
        List<Pago> pagosBBDD = servicio.consultarTodos();
        List<PagoDTO> pagoDTO = new ArrayList<>();
        for (Pago elemento:
                pagosBBDD) {
            PagoDTO pDTO = new PagoDTO();
            pagoDTO.add(pDTO.castPagoADto(elemento));
        }
        return new ResponseEntity<>(pagoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PagoDTO> crear(@Valid @RequestBody PagoDTO p) {
        Pago p1 = p.castPago();
        p1 = servicio.crear(p1);
        return new ResponseEntity<>(p.castPagoADto(p1), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> consultarUno(@PathVariable(name="id") int id){
        Pago p1 = servicio.consultaUno(id);
        if(p1 == null) {
            throw new PagoNoEncontrado("Pago no encontrado " + id);
        }
        return new ResponseEntity<>((new PagoDTO()).castPagoADto(p1), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<PagoDTO> modificar(@Valid @RequestBody PagoDTO p) {
        Pago p1 = servicio.consultaUno(p.getId());
        if (p1 == null) {
            throw new PagoNoEncontrado("Pago no encontrado " + p.getId());
        }
        p1 = servicio.modificar(p.castPago());
        return new ResponseEntity<>(p.castPagoADto(p1), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name="id") int id) {
        Pago p1 = servicio.consultaUno(id);
        if (p1 == null) {
            throw new PagoNoEncontrado("Pago no encontrado " + id);
        }
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
