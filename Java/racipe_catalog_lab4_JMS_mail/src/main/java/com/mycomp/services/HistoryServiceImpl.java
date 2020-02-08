package com.mycomp.services;


import com.mycomp.model.dao.HistoryDAO;
import com.mycomp.model.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private HistoryDAO historyDAO;

    @Autowired
    @Transactional
    public void setHistoryDAO(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    @Transactional
    public List<History> getAll() throws Exception {
        return historyDAO.getAll();
    }
    @Transactional
    public long add(History history) throws Exception {
       return historyDAO.add(history);
    }

    @Transactional
    public History findById(long id) throws Exception {
        return historyDAO.findById(id);
    }
    @Transactional
    public void update(History history) throws Exception {
       historyDAO.update(history);
    }
    @Transactional
    public void delete(History history) throws Exception {
        //////// Важный момент неободимо перезагрузить удаляемый объект в сессии удаления чтобы он закэшировался и был виден hhibernane
        /// иначе будет java.lang.IllegalArgumentException: Removing a detached instance

        History delete_h = findById(history.getId());
        historyDAO.delete(delete_h);
    }
}
