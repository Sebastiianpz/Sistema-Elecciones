package com.sample.core.service;

import java.util.List;

import com.sample.core.dao.VotosDao;
import com.sample.core.dao.VotosDaoImp;
import com.sample.core.domain.Voto;

public class VotosServiceImp implements VotosService {

    private VotosDao votosDao = new VotosDaoImp();

    @Override
    public List<Voto> list() throws Exception {
        return votosDao.list();
    }

    @Override
    public void save(int personaId, int candidatoId, int pcId) throws Exception {
        votosDao.save(personaId, candidatoId, pcId);
    }
}