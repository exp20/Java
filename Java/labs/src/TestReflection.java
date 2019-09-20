import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import t1.buildings.dwelling.Dwelling;
import t1.buildings.dwelling.Flat;
import t1.buildings.interfaces.Building;
import t1.buildings.interfaces.Floor;
import t1.buildings.interfaces.Space;
import t1.buildings.office.Office;
import t1.buildings.office.OfficeBuilding;
import t1.buildings.office.OfficeFloor;
import t1.utilities.Buildings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestReflection {
    public static void main(String... args){
        try {
            Class spaceClass = Class.forName("t1.buildings.dwelling.Flat");
            Space space1 = Buildings.createSpace(spaceClass, 5);
            System.out.println(space1);

            Class floorClass = Class.forName("t1.buildings.dwelling.DwellingFloor");
            Space[] arr = {new Flat(1,1), new Flat(2,2)};
            System.out.println(arr.getClass().getName());
            Floor floor1 = Buildings.createFloor(floorClass, arr);
            System.out.println(floor1);
            //Class building_class = Class.forName("t1.buildings.dwelling.Dwelling");
            Building build1 = Buildings.createBuilding((Class)Class.forName("t1.buildings.dwelling.Dwelling"), new Floor[]{floor1} );
            System.out.println(build1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fin = new FileInputStream("building_out_b.bin");
            System.out.println(Buildings.inputBuilding(fin, OfficeBuilding.class, OfficeFloor.class, Office.class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
