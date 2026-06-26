package com.sample.core.dao;

import com.sample.core.dao.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardDAO {

    private Connection conn;

    public DashboardDAO() {
        this.conn = Conexion.getInstance().dameConnection();
    }

    // ── Total de personas en el padrón ─────────────────────────────────
    public int getTotalPadron() throws Exception {
        String sql = "SELECT COUNT(*) FROM Personas";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // ── Personas habilitadas para votar ────────────────────────────────
    public int getHabilitados() throws Exception {
        String sql = "SELECT COUNT(*) FROM Personas WHERE habilitado_votar = true";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // ── Votos emitidos ─────────────────────────────────────────────────
    public int getVotosEmitidos() throws Exception {
        String sql = "SELECT COUNT(*) FROM votos";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // ── Personas deshabilitadas ────────────────────────────────────────
    public int getDeshabilitados() throws Exception {
        String sql = "SELECT COUNT(*) FROM Personas WHERE habilitado_votar = false";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // ── PCs habilitadas (equipos activos) ──────────────────────────────
    public int getEquiposActivos() throws Exception {
        String sql = "SELECT COUNT(*) FROM pcs_habilitadas WHERE habilitada = true";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // ── Resultados por candidato (votos + color) ───────────────────────
    // Devuelve lista de maps con: nombre_completo, partido, votos, color_partido
    public List<Map<String, Object>> getResultadosCandidatos() throws Exception {
        String sql = "SELECT c.nombre_completo, c.partido, c.color_partido, " +
                     "COUNT(v.id) AS votos " +
                     "FROM candidatos c " +
                     "LEFT JOIN votos v ON v.candidato_id = c.id " +
                     "GROUP BY c.id, c.nombre_completo, c.partido, c.color_partido " +
                     "ORDER BY votos DESC";

        List<Map<String, Object>> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("nombre_completo", rs.getString("nombre_completo"));
                fila.put("partido",         rs.getString("partido"));
                fila.put("color_partido",   rs.getString("color_partido"));
                fila.put("votos",           rs.getInt("votos"));
                lista.add(fila);
            }
        }
        return lista;
    }
}