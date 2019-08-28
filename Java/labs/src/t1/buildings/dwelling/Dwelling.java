/**
Класс описывающий жилое здание, основывающийся на массиве этажей DwellingFloor
*/
package t1.buildings.dwelling;

import t1.exceptions.*;
import t1.buildings.*;
import t1.buildings.interfaces.*;

public class Dwelling implements Building{
	private Floor[] floors_array;

	public Dwelling(int numbers_of_floors, int...arr_numbers_of_flats){
		this.floors_array = new Floor[numbers_of_floors];
		for(int i=0; i < numbers_of_floors; i++){
			this.floors_array[i]= new DwellingFloor(arr_numbers_of_flats[i]);
		}
	}
	public Dwelling(Floor...arr_dwelling_floor){
		this.floors_array=new Floor[arr_dwelling_floor.length];
		for(int i=0; i < arr_dwelling_floor.length; i++){
			this.floors_array[i] = arr_dwelling_floor[i];
		}
	}

	public int getTotalNumberOfFloors(){
		return this.floors_array.length;
	}

	public int getTotalNumberOfSpaces(){
		int total_number_of_flats=0;
		for (Floor e : this.floors_array){
			total_number_of_flats+=e.getTotalNumberOfSpaces();
		}
		return total_number_of_flats;
	}
	public double getTotalSquare(){
		double total_square=0;
		for(Floor e: this.floors_array){
			total_square+= e.getTotalSquare();
		}
		return total_square;
	}
	public int getTotalNumberOfRooms(){
		int total_number_of_rooms=0;
		for (Floor e : this.floors_array){
			total_number_of_rooms+=e.getTotalNumberOfRooms();
		}
		return total_number_of_rooms;
	}
	public Floor[] getFloorsArray(){
		return this.floors_array;
	}

	public Floor getFloor(int number_of_floor){
		if (number_of_floor < 0 || number_of_floor > this.floors_array.length-1) 
			throw new FloorIndexOutOfBoundsException();
		 else{
		 	return this.floors_array[number_of_floor];
		 }
	}
	//метод изменения этажа по его номеру в доме и ссылке на обновленный этаж.
	public void setFloor(int number_of_dwelling_floor, Floor new_dwelling_floor){
		if (number_of_dwelling_floor < 0 || number_of_dwelling_floor > this.floors_array.length-1)
			throw new FloorIndexOutOfBoundsException();
		else{
			Floor set_floor = this.floors_array[number_of_dwelling_floor];
			Space buff_space;
			Space buff_new_space;
			for (int i=0; i < set_floor.getTotalNumberOfSpaces(); i++){
				buff_space = set_floor.getSpace(i);
				buff_new_space = new_dwelling_floor.getSpace(i);
				buff_space.setNumberOfRooms(buff_new_space.getNumberOfRooms());
				buff_space.setSquare(buff_new_space.getSquare());
			}
		}
			
			
	}
	//метод получения объекта квартиры по ее номеру в доме
	public Space getSpace(int number_of_flat){
		if (number_of_flat < 0 || number_of_flat > this.getTotalNumberOfSpaces()-1)
			throw new SpaceIndexOutOfBoundsException();
		else{
			int[] flat_index=this.getFlatIndex(number_of_flat);
			return this.floors_array[flat_index[0]].getSpace(flat_index[1]);
			}
	}
	//метод изменения объекта квартиры по ее номеру в доме и ссылке типа квартиры.
	public void setSpace(int number_of_space, Space new_space){
		if (number_of_space<0 || number_of_space>this.getTotalNumberOfSpaces()-1)
			throw new SpaceIndexOutOfBoundsException();
		else{
			Space flat=this.getSpace(number_of_space);
			flat.setSquare(new_space.getSquare());
			flat.setNumberOfRooms(new_space.getNumberOfRooms());
	}
	
}// метод получения номера этажа на котором расположена квартира по номеру и номера квартиры относительно этого этажа
	private int[] getFlatIndex(int number_of_flat){
		int buff_flat_index=number_of_flat;
		for(int i =0; i < this.floors_array.length; i++){
			if (buff_flat_index<=this.floors_array[i].getTotalNumberOfSpaces()-1){
				return new int[]{i,buff_flat_index};
			}
			else{
				buff_flat_index-=this.floors_array[i].getTotalNumberOfSpaces();
			}
		}
		return null;
}
	//метод добавления квартиры в дом по будущему номеру квартиры в доме (т.е. в параметрах указывается номер, который должны иметь квартира после вставки) и ссылке на квартиру (количество этажей в доме при этом не увеличивается).
	public void addSpace(int number_of_new_flat, Space new_flat) {
		if(number_of_new_flat<0 || number_of_new_flat> this.getTotalNumberOfSpaces())
		 throw new SpaceIndexOutOfBoundsException("wrong new index");
		else{
			int buff_flat_index=number_of_new_flat;
			for(int i =0; i < this.floors_array.length; i++){
				if (buff_flat_index<=this.floors_array[i].getTotalNumberOfSpaces()){
					//нашли этаж на который её нужно добавить
					this.floors_array[i].addSpace(buff_flat_index,new_flat);
					return;
				}
				else{
					buff_flat_index-=this.floors_array[i].getTotalNumberOfSpaces();
				}
		}
			
	}
}
	//Создайте метод удаления квартиры по ее номеру в доме.
	public void dellSpace(int number_of_dell_flat){
		if(number_of_dell_flat<0 || number_of_dell_flat> this.getTotalNumberOfSpaces()-1)
			throw new SpaceIndexOutOfBoundsException();
		else{
			int[] dell_flat_index=this.getFlatIndex(number_of_dell_flat);
			this.floors_array[dell_flat_index[0]].dellSpace(dell_flat_index[1]);
		}
	}
	//метод получения самой большой по площади квартиры дома.
	public Space getBestSpace(){
		Space buff_best_space_flat = this.getSpace(0);
		for (Floor floor : this.floors_array){
			if (floor.getBestSpace().getSquare()>buff_best_space_flat.getSquare()){
				buff_best_space_flat=floor.getBestSpace();
			}
		}
		return buff_best_space_flat;
	}
	//метод получения отсортированного по убыванию площадей массива квартир.
	public Space[] getSortSpacesArray(){
		Space[] sort_flats_array= new Space[this.getTotalNumberOfSpaces()];
		int k = 0;
		for(Floor floor : this.floors_array){
			for(Space flat : floor.getSpacesArray()){
				sort_flats_array[k]=flat;
				k++;
			}
			
		}
		//сама сортировка


		for(int i=0; i < sort_flats_array.length; i++){

			for (int j = i+1; j< sort_flats_array.length; j++){
				if (sort_flats_array[i].getSquare()<sort_flats_array[j].getSquare()){
					Space buff = sort_flats_array[i];
					sort_flats_array[i]=sort_flats_array[j];
					sort_flats_array[j]=buff;
				}
			}
		}

		return sort_flats_array;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder("Dwelling ("+this.floors_array.length);
		for (Floor floor: this.floors_array){
			sb.append(", "+floor);
		}
		sb.append(" )");
		return sb.toString();
	}

}
