package com.mycomp.services;


import com.mycomp.model.entity.History;

import java.util.List;

public interface HistoryService{

    public long add(History history) throws Exception;

    public List<History> getAll() throws Exception;

    public History findById(long id) throws Exception;

    public void update(History history) throws Exception;

    public void delete(History history) throws Exception;
}