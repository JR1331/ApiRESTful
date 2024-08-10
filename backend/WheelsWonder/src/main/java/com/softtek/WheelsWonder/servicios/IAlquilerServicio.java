package com.softtek.WheelsWonder.servicios;

import com.softtek.WheelsWonder.modelo.Alquiler;

import java.util.List;

public interface IAlquilerServicio extends ICRUD<Alquiler,Integer>{
    List<Alquiler> findByUsuarioAlquiler_Id(Integer id_usuario);
}
