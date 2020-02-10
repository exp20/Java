package com.mycomp.services;

import com.mycomp.model.dao.EmailHistoryDAO;
import com.mycomp.model.entity.EmailHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmailHistoryServiceImpl implements EmailHistoryService {

    @Autowired
    EmailHistoryDAO emailHistoryDAO;

    @Transactional
    public List<EmailHistory> getAll() throws Exception {
        return emailHistoryDAO.getAll();
    }
    @Transactional
    public long add(EmailHistory history) throws Exception {
        return emailHistoryDAO.add(history);
    }

    @Transactional
    public EmailHistory findById(long id) throws Exception {
        return emailHistoryDAO.findById(id);
    }
    /*
    @Transactional
    public void update(History history) throws Exception {
       historyDAO.update(history);
    }*/
    @Transactional
    public void delete(EmailHistory history) throws Exception {
        //////// Важный момент неободимо перезагрузить удаляемый объект в сессии удаления чтобы он закэшировался и был виден hhibernane
        /// иначе будет java.lang.IllegalArgumentException: Removing a detached instance
        EmailHistory delete_h = findById(history.getId());
        emailHistoryDAO.delete(delete_h);
    }
}
