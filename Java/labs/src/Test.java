
import java.util.Arrays;
import t1.buildings.*;
import t1.*;
import static java.lang.System.out;
public class Test{

public static void main(String[] arg){
	
	//тесты классов Flat, DwellingFloor, Dwelling;
	/**
	Flat f1= new Flat();
	System.out.println(f1);
	f1 = new Flat(22,55);
	System.out.println(f1);

	DwellingFloor floor1= new DwellingFloor(5);
	System.out.println(floor1);

	Flat[] arr1_flat= {new Flat(28,50), new Flat(16,90), new Flat(9,12), new Flat(1,1)};
	floor1= new DwellingFloor(arr1_flat);
	System.out.println(floor1);
	System.out.println("getNumbersOfFlats= "+floor1.getNumbersOfFlats()+" getTotalSquare= "+floor1.getTotalSquare()+" getTotalNumbersOfRooms= "+floor1. getTotalNumbersOfRooms() + " getBestSpace= "+ floor1.getBestSpace());
	
	Flat[] return_arr_flat=floor1.getFlatsArray();

	System.out.println("getFlatByNumber(3) = "+floor1.getFlatByNumber(3));
	floor1.setFlat(3, new Flat(2,2));

	floor1.addFlatToFloor(4,new Flat(4,4));
	floor1.addFlatToFloor(3,new Flat(3,3));
	floor1.addFlatToFloor(0,new Flat(0,0));
	System.out.println(floor1);

	floor1.dellFlat(0);
	floor1.dellFlat(2);
	floor1.dellFlat(4);
	System.out.println(floor1);


	Dwelling dwell1 = new Dwelling (3,new int[]{3,2,1});
	System.out.println(dwell1);

	DwellingFloor floor2= new DwellingFloor(arr1_flat);
	dwell1 = new Dwelling(new DwellingFloor[]{floor1, floor2});
	System.out.println(dwell1);

	System.out.println("getNumbersOfFloor = " + dwell1.getNumbersOfFloor()+ " getTotalNumberOfFlats = "+ dwell1.getTotalNumberOfFlats()+ " getTotalSquare = " +dwell1.getTotalSquare() + " getTotalNumbersOfRooms = "+ dwell1.getTotalNumbersOfRooms());

	dwell1.getDwellingFloorsArr();

	System.out.println("getDwellingFloorByNumber (0)" + dwell1.getDwellingFloorByNumber(0) + " getDwellingFloorByNumber(1) = "+ dwell1.getDwellingFloorByNumber(1));

	DwellingFloor floor3= new DwellingFloor( new Flat[] {new Flat(10,10), new Flat(11,11)});
	dwell1.setDwellingFloor(1,floor3);
	System.out.println(dwell1);

	System.out.println("getFlatByNumber (getTotalNumberOfFlats()-1) ="+ dwell1.getFlatByNumber(dwell1.getTotalNumberOfFlats()-1));
	System.out.println("setFlat (getTotalNumberOfFlats()-1) " );
	dwell1.setFlat(dwell1.getTotalNumberOfFlats()-1, new Flat(99,99));
	System.out.println(dwell1);

	System.out.println("addFlat (getTotalNumberOfFlats()-1+1)");
	dwell1.addFlat(dwell1.getTotalNumberOfFlats()-1+1, new Flat (100,100));

	System.out.println("dellFlat (0)");
	dwell1.dellFlat(0);
	System.out.println(dwell1);

	System.out.println("getBestSpace() = "+ dwell1.getBestSpace());

	Flat[] sort_arr = dwell1.getSortArray();

	System.out.println(dwell1);
	for (int i = 0; i < sort_arr.length; i++){
		System.out.println(sort_arr[i]);
	}

	int[] s = new int[]{10,2,31,99,100};
	for(int i=0; i < s.length; i++){
			for (int j = i+1; j< s.length; j++){
				if (s[i]<s[j]){
					int buff = s[i];
					s[i]=s[j];
					s[j]=buff;
				}
			}
		}
		System.out.println(Arrays.toString(s));
*/

/**
	Office of1= new Office();
	out.println(of1);
	of1.setSquare(100);
	of1.setNumberOfRooms(3);
	out.println(of1);

	OfficeFloor of_floor1=new OfficeFloor(3);
	
	of_floor1.setOffice(0,new Office(1,1));
	of_floor1.setOffice(1,new Office(2,2));
	of_floor1.setOffice(2,new Office(3,3));
	out.println(of_floor1);

	OfficeFloor of_floor2= new OfficeFloor( new Office[]{new Office(99,99), new Office(88,88), new Office(77,77)});
	out.println(of_floor2);
	out.println("кол-во оффисов of_floor1 "+ of_floor1.getNumberOfOffices()+" общаяя площадь " + of_floor1.getTotalSquare()+" кол-во комнат "+ of_floor1.getTotalNumberOfRooms()+ " getBestSpace() "+ of_floor1.getBestSpace());
	
	Office[] off_array = of_floor1.getOfficesArray();
	out.println(off_array[2]);

	of_floor1.addOfficeToFloor(3, new Office(10,10));
	of_floor1.addOfficeToFloor(0, new Office(0,0));
	of_floor1.addOfficeToFloor(2, new Office(22,22));
	out.println(of_floor1);

	out.println("удаление");
	of_floor1.dellOffice(2);
	of_floor1.dellOffice(0);
	of_floor1.dellOffice(of_floor1.getNumberOfOffices()-1);
	out.println(of_floor1);
	*/
	
	OfficeBuilding of_build = new OfficeBuilding(3, new int[]{3,3,2});
	out.println(of_build);

	OfficeBuilding of_build2 = new OfficeBuilding(new OfficeFloor[]{new OfficeFloor(3),new OfficeFloor(2), new OfficeFloor(1)});
	out.println(of_build2);
	
	out.println("getNumberOfFloors = "+ of_build2.getNumberOfFloors()+" getTotalNumberOfOffices = "+of_build2.getTotalNumberOfOffices()+ " getTotalSquare = "+ of_build2.getTotalSquare()+ " getTotalNumberOfRooms = "+ of_build2.getTotalNumberOfRooms());
	OfficeFloor[] office_arr1= of_build2.getOfficeFloorsArray();
	for(OfficeFloor of_fl:office_arr1){
		out.println(of_fl);
	}

	out.println(of_build2.getOfficeFloor(2));
	of_build2.setOfficeFloor(1, new OfficeFloor(4));
	of_build2.setOfficeFloor(0, new OfficeFloor(1));
	of_build2.setOfficeFloor(2, new OfficeFloor(2));
	out.println(of_build2);

	out.println(of_build2.getOfficeByNumber(0));
	out.println(of_build2.getOfficeByNumber(of_build2.getTotalNumberOfOffices()-1));

	of_build2.setOffice(of_build2.getTotalNumberOfOffices()-1,new Office(99,990));
	out.println(of_build2);

	of_build2.addOffice(of_build2.getTotalNumberOfOffices(), new Office(100,100));
	of_build2.addOffice(0, new Office(1,1));
	out.println("\n"+of_build2);

	of_build2.dellOffice(0);
	of_build2.dellOffice(of_build2.getTotalNumberOfOffices()-1);
	out.println("\n"+of_build2);

	out.println(" getBestSpace = "+of_build2.getBestSpace());

	Office[] sort_of_build2= of_build2.getSortArray();
	for(Office of:sort_of_build2){
		out.println(of.getSquare());
	}

	}
}