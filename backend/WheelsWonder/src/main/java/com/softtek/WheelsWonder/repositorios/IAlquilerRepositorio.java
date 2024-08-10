package com.softtek.WheelsWonder.repositorios;

import com.softtek.WheelsWonder.modelo.Alquiler;

import java.util.List;

public interface IAlquilerRepositorio extends IGenericoRepositorio<Alquiler, Integer> {
    List<Alquiler> findByusuarioAlquiler_Id(Integer id_usuario);
}
