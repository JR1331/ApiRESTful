package com.softtek.WheelsWonder.repositorios;

import com.softtek.WheelsWonder.modelo.Valoracion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IValoracionRepositorio extends IGenericoRepositorio<Valoracion, Integer> {
    @Query("SELECT COUNT(v) FROM Valoracion v WHERE v.usuarioValoracion.id = :id_usuario")
    long countByUsuarioValoracion_Id(Integer id_usuario);

    @Query("SELECT AVG(v.puntuacion) FROM Valoracion v WHERE v.usuarioValoracion.id = :id_usuario")
    Double findAveragePuntuacionByUsuarioValoracion_Id(Integer id_usuario);
}
