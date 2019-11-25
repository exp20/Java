import com.mycom.entity.Doctor;
import com.mycom.entity.Patient;
import com.mycom.entity.Recipe;
import com.mycom.services.DoctorService;
import com.mycom.services.RecipeService;
import org.hibernate.SessionFactory;
import com.mycom.services.PatientService;
import com.mycom.utils.HibernateSession;




import java.sql.Date;


public class Test {
    public static void main(String ... args){
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = HibernateSession.getSessionFactory();


        DoctorService doctorService = new DoctorService(sessionFactory);
       RecipeService recipeService = new RecipeService(sessionFactory);
       PatientService patientService = new PatientService(sessionFactory);
       System.out.println(patientService.getAll());
       System.out.println(recipeService.getAll());
       System.out.println(doctorService.getAll());

       //create

        Doctor d1 = new Doctor("d1","d1","d1", "d1_spec");
        Patient p1 = new Patient("p1","p1","p1", "p99");
        Recipe r1 = new Recipe("r1",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),"priprity1",d1,p1);
        /*
        doctorService.add(d1);
        patientService.add(p1);
        recipeService.add(r1);
        */

        // delete
            //doctorService.delete(doctorService.findById(4)); // искл так как налож ограниченеие
          //  patientService.delete(patientService.findById(6));
            //recipeService.delete(recipeService.findById(7));
        //update
        /*
        Doctor upd = doctorService.findById(0);
        upd.setHonestly("changed отчество");
        doctorService.update(upd);
        */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
