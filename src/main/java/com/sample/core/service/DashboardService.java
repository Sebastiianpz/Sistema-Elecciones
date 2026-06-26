package com.sample.core.service;

import com.sample.core.dao.DashboardDAO;
import java.util.List;
import java.util.Map;

public class DashboardService {

    private DashboardDAO dao;

    public DashboardService() {
        this.dao = new DashboardDAO();
    }

    public int getTotalPadron() throws Exception {
        return dao.getTotalPadron();
    }

    public int getHabilitados() throws Exception {
        return dao.getHabilitados();
    }

    public int getVotosEmitidos() throws Exception {
        return dao.getVotosEmitidos();
    }

    public int getDeshabilitados() throws Exception {
        return dao.getDeshabilitados();
    }

    public int getEquiposActivos() throws Exception {
        return dao.getEquiposActivos();
    }

    // Participación = (votos emitidos / habilitados) * 100
    public double getParticipacion() throws Exception {
        int habilitados = dao.getHabilitados();
        int votos       = dao.getVotosEmitidos();
        if (habilitados == 0) return 0.0;
        return Math.round(((double) votos / habilitados) * 1000.0) / 10.0;
    }

    public List<Map<String, Object>> getResultadosCandidatos() throws Exception {
        return dao.getResultadosCandidatos();
    }
}