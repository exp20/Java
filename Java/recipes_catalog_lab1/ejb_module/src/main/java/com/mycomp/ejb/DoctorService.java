package com.mycomp.ejb;


import com.mycomp.domain.Doctor;
import javax.ejb.Local;

import java.util.List;

@Local
public interface DoctorService {

    public long add(Doctor doctor) throws Exception;

    public List<Doctor> getAll() throws Exception;

   public Doctor findById(long id) throws Exception;

    public void update(Doctor doctor) throws Exception;

   public void delete(Doctor doctor) throws Exception;
}
