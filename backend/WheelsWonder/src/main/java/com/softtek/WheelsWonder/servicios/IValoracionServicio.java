package com.softtek.WheelsWonder.servicios;

import com.softtek.WheelsWonder.modelo.Valoracion;

public interface IValoracionServicio extends ICRUD<Valoracion,Integer>{
    long countByUsuarioValoracion_Id(Integer id_usuario);
    Double findAveragePuntuacionByUsuarioValoracion_Id(Integer id_usuario);
}
