package com.sample.core.service;

import java.sql.Date;
import java.util.List;

import com.sample.core.dao.UsuarioDao;
import com.sample.core.dao.UsuarioDaoImpl;
import com.sample.core.domain.Usuario;

public class UsuarioServiceImp implements UsuarioService{


private UsuarioDao usuarioDao = new UsuarioDaoImpl();

@Override
public Usuario loginAdmin(String usuario, String contrasena) throws Exception {
    return usuarioDao.loginAdmin(usuario, contrasena);
}

@Override
public Usuario findCandidatoByid(int id) throws Exception {
    return usuarioDao.findCandidatoByid(id);
}

@Override
public List<Usuario> listCandidatos() throws Exception {
    return usuarioDao.listCandidatos();
}

@Override
public void saveCandidato(String nombreCompleto, String partido, int numPartido,String colorPartido) throws Exception {
    usuarioDao.saveCandidato( nombreCompleto, partido, numPartido,colorPartido
    );
}

@Override
public void updateCandidato(Usuario can) throws Exception {
    usuarioDao.updateCandidato(can);
}

@Override
public Usuario findEquipoByid(int id) throws Exception {
    return usuarioDao.findEquipoByid(id);
}

@Override
public List<Usuario> listEquipos() throws Exception {
    return usuarioDao.listEquipos();
}

@Override
public void saveEquipos(String macAddress, String nombreMac,boolean estadoPc, int votosEmitidos,Date fechaRegistro) throws Exception {
    usuarioDao.saveEquipos( macAddress, nombreMac, estadoPc,votosEmitidos,fechaRegistro );
}

@Override
public void deleteEquipos(int id) throws Exception {
    usuarioDao.deleteEquipos(id);
}

@Override
public void cambiarEstadoEquipos(Usuario mac) throws Exception {
    usuarioDao.cambiarEstadoEquipos(mac);
}
}
