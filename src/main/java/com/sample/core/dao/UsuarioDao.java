package com.sample.core.dao;

import java.util.List;
import com.sample.core.domain.Usuario;

public interface UsuarioDao {
    public Usuario loginAdmin(String usuario, String contrasena) throws Exception;
    public Usuario findCandidatoByid(int id) throws Exception;
    public List<Usuario> listCandidatos() throws Exception;
    public void saveCandidato(String nombreCompleto, String partido, int numPartido, String colorPartido) throws Exception;
    public void updateCandidato(Usuario can) throws Exception;
    public void deleteCandidato(int id) throws Exception;
    public Usuario findEquipoByid(int id) throws Exception;
    public List<Usuario> listEquipos() throws Exception;
    public void saveEquipos(String macAddress, String nombreMac, boolean estadoPc, int votosEmitidos) throws Exception;
    public void deleteEquipos(int id) throws Exception;
    public void cambiarEstadoEquipos(int id, boolean habilitada) throws Exception;
    public void actualizarDatosEquipo(Usuario mac) throws Exception;
}