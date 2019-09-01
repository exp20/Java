import t1.buildings.dwelling.Dwelling;
import t1.buildings.dwelling.DwellingFloor;
import t1.buildings.dwelling.Flat;
import t1.buildings.interfaces.Building;
import t1.buildings.interfaces.Floor;
import t1.buildings.office.Office;
import t1.buildings.office.OfficeBuilding;
import t1.buildings.office.OfficeFloor;

public class Test2 {
    public static void main(String... args){
        Flat flat1 = new Flat(10,2.1);
        Flat flat2 = new Flat(10,2.1);
        Office office1= new Office(10,2.1);
        System.out.printf("Equals? "+flat1+" "+flat2.toString()+" "+flat1.equals(flat2)+" hashCodes: %d, %d", flat1.hashCode(), flat2.hashCode());
        System.out.printf("\nEquals? "+flat1+" "+office1+" "+flat1.equals(office1)+" "+"hashCodes: %d, %d", flat1.hashCode(), office1.hashCode());

        Building dwelling1 = new Dwelling(new Floor[]{new DwellingFloor(new Flat(1,1), new Flat(1,1))});
        Building office_build1 = new OfficeBuilding(new Floor[]{new OfficeFloor(new Flat(1,1), new Flat(1,1))});
        System.out.println(dwelling1.equals("\n"+office_build1));

        OfficeBuilding clone_office_build1 = (OfficeBuilding) office_build1.clone();
        System.out.println("Клонирование");
        System.out.printf("\nОригинал (хэш = %d)\n"+office_build1,office_build1.hashCode());
        System.out.printf("\nКлон (хэш = %d)\n"+clone_office_build1,clone_office_build1.hashCode());
        office_build1.getSpace(0).setSquare(777);
        System.out.println(office_build1);

    }

}
