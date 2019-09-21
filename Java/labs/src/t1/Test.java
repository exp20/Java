package t1;

import t1.buildings.dwelling.*;
import t1.buildings.dwelling.Dwelling;
import t1.buildings.dwelling.DwellingFloor;
import t1.buildings.dwelling.Flat;
import t1.buildings.dwelling.hotel.Hotel;
import t1.buildings.interfaces.Building;
import t1.buildings.interfaces.Floor;
import t1.buildings.interfaces.Space;
import t1.buildings.office.Office;
import t1.buildings.office.OfficeBuilding;
import t1.buildings.office.OfficeFloor;
import t1.utilities.Buildings;
import t1.buildings.office.*;

import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;

import static java.lang.System.out;
import static java.lang.System.setOut;

public class Test{

public static void main(String[] arg){
	
	//тесты классов Flat, DwellingFloor, Dwelling;
	/*
	Flat f1 = new Flat();
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
	floor1.addFlatToFloor(0,new Flat(10,10));
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

	System.out.println("getNumbersOfFloor = " + dwell1.getNumberOfFloors()+ " getTotalNumberOfFlats = "+ dwell1.getTotalNumberOfFlats()+ " getTotalSquare = " +dwell1.getTotalSquare() + " getTotalNumbersOfRooms = "+ dwell1.getTotalNumbersOfRooms());

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
	of_floor1.addOfficeToFloor(0, new Office(1,1));
	of_floor1.addOfficeToFloor(2, new Office(22,22));
	out.println(of_floor1);

	out.println("удаление");
	of_floor1.dellOffice(2);
	of_floor1.dellOffice(0);
	of_floor1.dellOffice(of_floor1.getNumberOfOffices()-1);
	out.println(of_floor1);

	
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
	//of_build2.dellOffice(-1);

	 */
/*
	// Тесты посл едобавления интерфейсов
	Space[] space_array1 = new Space[]{new Office(1,1), new Flat(2,2)};
	for(Space s: space_array1){
		out.println(s);
	}
	OfficeFloor of_floor = new OfficeFloor(2);
	of_floor.addSpace(2,new Flat(1));
	out.println(of_floor);
*/
	DwellingFloor dw_floor = new DwellingFloor(new Space[]{new Flat(2,2), new Office(1,1)});
	dw_floor.addSpace(2,new Office(3,3));
	out.println(dw_floor);

	OfficeFloor dw_floor2 = new OfficeFloor(new Space[]{new Office(71,71), new Flat(72,72)});
	Building building1 = new Dwelling(new Floor[]{dw_floor, dw_floor2});
	out.println(building1);
	Space[] sp_arr=building1.getSortSpacesArray();
	for(Space s : sp_arr){
		out.println(s);
	}

	OfficeFloor officeFloor3 = new OfficeFloor(new Office[]{new Office(1,1)});
	OfficeFloor officeFloor4 = new OfficeFloor(new Flat[]{new Flat(1,1)});
	out.println(officeFloor3);
	out.println(officeFloor4);



	Building building2 = new Dwelling(new Floor[]{ officeFloor3, officeFloor4, dw_floor, dw_floor2});
	out.println(building2);
	out.println("getBestSpace = "+building2.getBestSpace()+" getTotalNumberOfSpaces "+ building2.getTotalNumberOfSpaces()+ " getTotalNumberOfRooms "+building2.getTotalNumberOfRooms()
	+ " getSpace(0) " + building2.getSpace(0)+ "getTotalNumberOfFloors "+ building2.getTotalNumberOfFloors() + " getTotalSquare "+ building2.getTotalSquare());

	building2.addSpace(0,new Flat(99,99));
	out.println("dell space ");
	building2.dellSpace(building2.getTotalNumberOfSpaces()-1);
	out.println(building2);


	Building office_build1 = new OfficeBuilding(new Floor[]{ officeFloor3, officeFloor4, dw_floor, dw_floor2});
	out.println(office_build1);
	out.println("getBestSpace = "+office_build1.getBestSpace()+" getTotalNumberOfSpaces "+ office_build1.getTotalNumberOfSpaces()+ " getTotalNumberOfRooms "+office_build1.getTotalNumberOfRooms()
			+ " getSpace(0) " + office_build1.getSpace(0)+ "getTotalNumberOfFloors "+ office_build1.getTotalNumberOfFloors() + " getTotalSquare "+ office_build1.getTotalSquare());

	office_build1.addSpace(0,new Flat(99,99));
	out.println("add SPAcE FFF\n"+office_build1);
	office_build1.addSpace(office_build1.getTotalNumberOfSpaces()-1, new Flat(100,100));
	office_build1.dellSpace(office_build1.getTotalNumberOfSpaces()-1);
	out.println("dell SPAcE FFF\n"+office_build1);
	out.println("\n"+office_build1);

	Hotel hotel1 = new Hotel(new OfficeFloor(4));
	OfficeBuilding office_build2 = new OfficeBuilding(new Floor[]{new DwellingFloor(3), new OfficeFloor(3)});
	Dwelling building3 = new Dwelling(new Floor[] {new DwellingFloor(2), new DwellingFloor(2)});

// Тест ввода-вывода
	/*
	try (FileOutputStream fout = new FileOutputStream("../out/building_out_b.bin")){
		Buildings.outputBuilding(office_build2,fout); //Office
		Buildings.outputBuilding(building3,fout); //Dwelling
		Buildings.outputBuilding(hotel1,fout); // Hotel

	} catch (IOException e) {
		e.printStackTrace();
	}



	try( FileInputStream fin = new FileInputStream("../out/building_out_b.bin")){
		Building r_building = Buildings.inputBuilding(fin);
		out.println( "\n\n"+r_building);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	catch (IOException e) {
		e.printStackTrace();
	}
*/
	Building building4 = new Dwelling( new Floor[] { new OfficeFloor(new Office()), new DwellingFloor(new Flat()) });
			//try (Writer wr = new OutputStreamWriter(System.out)){
	try (Writer wr = new FileWriter("../out/building_out_s.txt")){
		Buildings.writeBuilding(office_build2,wr);
		wr.write("\n");
		Buildings.writeBuilding(building3,wr);
		wr.write("\n");
		Buildings.writeBuilding(hotel1,wr);
		wr.write("\n");
	} catch (IOException e) {
		e.printStackTrace();
	}

/*
try (Reader r = new FileReader("../out/building_out_s.txt")){
	Building read_build = Buildings.readBuilding(r);
	out.println(read_build);
} catch (FileNotFoundException e) {
	e.printStackTrace();
} catch (IOException e) {
	e.printStackTrace();
}



//Тесты сериализации
	Building build_ser = new Dwelling(new Floor[]{
			new OfficeFloor(
					new Flat(1,1),
					new Office(2,2)),
			new DwellingFloor(
					new Office(3,3),
					new Flat(4,4))});
	try( FileOutputStream fout  = new FileOutputStream("../out/building.dat")){
		Buildings.serializeBuilding(build_ser, fout);
		out.println("\n Сериализуем\n"+build_ser);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
 try (FileInputStream fin = new FileInputStream("../out/building.dat")){
 		Building deserial_building = Buildings.deserialaizeBuilding(fin);
	 out.println("\nПолучили после десериализации\n");
	 out.println(deserial_building);
	 }
 	catch (ClassNotFoundException e){
 	e.printStackTrace();
 } catch (IOException e) {
	 e.printStackTrace();
 }

 try (FileWriter fl_writer =  new FileWriter("../out/building_format.txt")) {
	 Buildings.writeBuildingFormat(build_ser,fl_writer );
 } catch (IOException e) {
	 e.printStackTrace();
 }
	try (Scanner scanner =  new Scanner(new FileInputStream("../out/building_format.txt"))) {
		out.println(Buildings.readBuilding(build_ser,scanner ));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}

*/

}
}