/**
Класс описывающий жилое здание, основывающийся на массиве этажей DwellingFloor
*/
package t1.buildings;

import t1.buildings.*;

public class Dwelling{
	private DwellingFloor[] floors_array;

	public Dwelling(int numbers_of_floors, int[] arr_numbers_of_flats){
		this.floors_array = new DwellingFloor[numbers_of_floors];
		for(int i=0; i < numbers_of_floors; i++){
			this.floors_array[i]= new DwellingFloor(arr_numbers_of_flats[i]);
		}
	}
	public Dwelling(DwellingFloor[] arr_dwelling_floor){
		this.floors_array=new DwellingFloor[arr_dwelling_floor.length];
		for(int i=0; i < arr_dwelling_floor.length; i++){
			this.floors_array[i] = new DwellingFloor(arr_dwelling_floor[i].getFlatsArray());
		}
	}
	public int getNumbersOfFloor(){
		return this.floors_array.length;
	}
	public int getTotalNumberOfFlats(){
		int total_number_of_flats=0;
		for (DwellingFloor e : this.floors_array){
			total_number_of_flats+=e.getNumbersOfFlats();
		}
		return total_number_of_flats;
	}
	public double getTotalSquare(){
		double total_square=0;
		for(DwellingFloor e: this.floors_array){
			total_square+= e.getTotalSquare();
		}
		return total_square;
	}
	public int getTotalNumbersOfRooms(){
		int total_number_of_rooms=0;
		for (DwellingFloor e : this.floors_array){
			total_number_of_rooms+=e.getTotalNumbersOfRooms();
		}
		return total_number_of_rooms;
	}
	public DwellingFloor[] getDwellingFloorsArr(){
		return this.floors_array;
	}

	public DwellingFloor getDwellingFloorByNumber(int number_of_floor){
		if (number_of_floor<0 || number_of_floor > this.floors_array.length-1) System.out.println("getDwellingFloorByNumber: wrong dwellingfloor number");
		 else{
		 	return this.floors_array[number_of_floor];
		 }
		 return null;
	}
	//метод изменения этажа по его номеру в доме и ссылке на обновленный этаж.
	public void setDwellingFloor(int number_of_dwelling_floor, DwellingFloor new_dwelling_floor){
		if (number_of_dwelling_floor<0 || number_of_dwelling_floor>this.floors_array.length-1) System.out.println("setDwellingFloor: wrong number_of_dwelling_floor");
		else{
			this.floors_array[number_of_dwelling_floor]=new_dwelling_floor;
		}
			
			
	}
	//метод получения объекта квартиры по ее номеру в доме
	public Flat getFlatByNumber(int number_of_flat){
		if (number_of_flat<0 || number_of_flat>this.getTotalNumberOfFlats()-1) System.out.println("getFlatByNumber: wrong number_of_flat");
		else{
			int[] flat_index=this.getFlatIndex(number_of_flat);
			return this.floors_array[flat_index[0]].getFlatByNumber(flat_index[1]);
			}
		return null;
	}
	//метод изменения объекта квартиры по ее номеру в доме и ссылке типа квартиры.
	public void setFlat(int number_of_flat, Flat new_flat){
		if (number_of_flat<0 || number_of_flat>this.getTotalNumberOfFlats()-1) System.out.println("setFlat: wrong number_of_flat");
		else{
			Flat flat=this.getFlatByNumber(number_of_flat);
			flat.setSquare(new_flat.getSquare());
			flat.setNumberOfRooms(new_flat.getNumberOfRooms());
	}
	
}// метод получения номера этажа на котором расположена квартира по номеру и номера квартиры относительно этого этажа
	private int[] getFlatIndex(int number_of_flat){
		int buff_flat_index=number_of_flat;
		for(int i =0; i < this.floors_array.length; i++){
			if (buff_flat_index<=this.floors_array[i].getNumbersOfFlats()-1){
				return new int[]{i,buff_flat_index};
			}
			else{
				buff_flat_index-=this.floors_array[i].getNumbersOfFlats();
			}
		}
		return null;
}
	//метод добавления квартиры в дом по будущему номеру квартиры в доме (т.е. в параметрах указывается номер, который должны иметь квартира после вставки) и ссылке на квартиру (количество этажей в доме при этом не увеличивается).
	public void addFlat(int number_of_new_flat, Flat new_flat) {
		if(number_of_new_flat<0 || number_of_new_flat> this.getTotalNumberOfFlats()) System.out.println("addFlat: wrong number_of_new_flat");
		else{
			int buff_flat_index=number_of_new_flat;
			for(int i =0; i < this.floors_array.length; i++){
				if (buff_flat_index<=this.floors_array[i].getNumbersOfFlats()){
					//нашли этаж на который её нужно добавить
					this.floors_array[i].addFlatToFloor(buff_flat_index,new_flat);
				}
				else{
					buff_flat_index-=this.floors_array[i].getNumbersOfFlats();
				}
		}
			
	}
}
	//Создайте метод удаления квартиры по ее номеру в доме.
	public void dellFlat(int number_of_dell_flat){
		if(number_of_dell_flat<0 || number_of_dell_flat> this.getTotalNumberOfFlats()-1) System.out.println("dellFlat: wrong number_of_flat");
		else{
			int[] dell_flat_index=this.getFlatIndex(number_of_dell_flat);
			this.floors_array[dell_flat_index[0]].dellFlat(dell_flat_index[1]);
		}
	}
	//метод получения самой большой по площади квартиры дома.
	public Flat getBestSpace(){
		Flat buff_best_space_flat = new Flat(1,0);
		for (DwellingFloor floor : this.floors_array){
			if (floor.getBestSpace().getSquare()>buff_best_space_flat.getSquare()){
				buff_best_space_flat=floor.getBestSpace();
			}
		}
		return buff_best_space_flat;
	}
	//метод получения отсортированного по убыванию площадей массива квартир.
	public Flat[] getSortArray(){
		Flat[] sort_flats_array= new Flat[this.getTotalNumberOfFlats()];
		int k = 0;
		for(DwellingFloor floor : this.floors_array){
			for(Flat flat : floor.getFlatsArray()){
				sort_flats_array[k]=new Flat(flat.getNumberOfRooms(),flat.getSquare());
				k++;
			}
			
		}
		//сама сортировка


		for(int i=0; i < sort_flats_array.length; i++){

			for (int j = i+1; j< sort_flats_array.length; j++){
				if (sort_flats_array[i].getSquare()<sort_flats_array[j].getSquare()){
					Flat buff = sort_flats_array[i];
					sort_flats_array[i]=sort_flats_array[j];
					sort_flats_array[j]=buff;
				}
			}
		}

		return sort_flats_array;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder("Dwelling ("+this.floors_array.length);
		for (DwellingFloor floor: this.floors_array){
			sb.append(", "+floor);
		}
		sb.append(" )");
		return sb.toString();
	}

}
