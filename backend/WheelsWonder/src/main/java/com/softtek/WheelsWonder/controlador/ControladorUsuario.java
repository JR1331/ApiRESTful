package com.softtek.WheelsWonder.controlador;

import com.softtek.WheelsWonder.dto.UsuarioDTO;
import com.softtek.WheelsWonder.excepciones.UsuarioNoEncontrado;
import com.softtek.WheelsWonder.modelo.Usuario;
import com.softtek.WheelsWonder.modelo.Vehiculo;
import com.softtek.WheelsWonder.servicios.IUsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorUsuario {

    @Autowired
    private IUsuarioServicio servicio;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> consultarTodos(){
        List<Usuario> usuariosBBDD = servicio.consultarTodos();
        List<UsuarioDTO> usuarioDTO = new ArrayList<>();
        for (Usuario elemento:
        usuariosBBDD) {
            UsuarioDTO uDTO = new UsuarioDTO();
            usuarioDTO.add(uDTO.castUsuarioADto(elemento));
        }
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

   /* @GetMapping("/pagina")
    public ResponseEntity<Page<Usuario>> consultarPagina(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> usuarios = servicio.consultarPagina(pageable);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@Valid @RequestBody UsuarioDTO u) {
        Usuario u1 = u.castUsuario();
        u1 = servicio.crear(u1);
        return new ResponseEntity<>(u.castUsuarioADto(u1), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> consultarUno(@PathVariable(name="id") int id){
        Usuario u1 = servicio.consultaUno(id);
        if(u1 == null) {
            throw new UsuarioNoEncontrado("Usuario no encontrado " + id);
        }
        return new ResponseEntity<>((new UsuarioDTO()).castUsuarioADto(u1), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> modificar(@Valid @RequestBody UsuarioDTO u) {
        Usuario u1 = servicio.consultaUno(u.getId());
        if (u1 == null) {
            throw new UsuarioNoEncontrado("usuario no encontrado " + u.getId());
        }
        u1 = servicio.modificar(u.castUsuario());
        return new ResponseEntity<>(u.castUsuarioADto(u1), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name="id") int id) {
        Usuario u1 = servicio.consultaUno(id);
        if (u1 == null) {
            throw new UsuarioNoEncontrado("usuario no encontrado " + id);
        }
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pordni")
    public ResponseEntity<List<Usuario>>findByDni(@RequestParam(name="dni") String dni) throws Exception{
        return new ResponseEntity<>(servicio.findByDni(dni),HttpStatus.OK);
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<Optional<Usuario>>findByCorreo(@PathVariable(name="correo") String correo) throws Exception{
        return new ResponseEntity<>(servicio.findByCorreo(correo),HttpStatus.OK);
    }
}
