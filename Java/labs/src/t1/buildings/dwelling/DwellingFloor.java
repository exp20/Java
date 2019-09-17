/**
Класс описывающий этаж жилого здания
*/
package t1.buildings.dwelling;
import t1.buildings.interfaces.*;
import t1.exceptions.*;
import t1.buildings.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DwellingFloor implements Floor, Serializable, Cloneable, Iterable<Space> {
	private Space[] space_array;

	public DwellingFloor(int number_of_flats) {
		this.space_array = new Flat[number_of_flats];
		for (int i = 0; i < number_of_flats; i++) {
			space_array[i] = new Flat();
		}
	}

	public DwellingFloor(Space... new_space_array) {
		/*
		this(new_flats_array.length);
		for (int i=0; i < new_flats_array.length; i++){
			this.flats_array[i].setNumberOfRooms(new_flats_array[i].getNumberOfRooms());
			this.flats_array[i].setSquare(new_flats_array[i].getSquare());
		}
		*/
		this.space_array = new_space_array;
	}

	public int getTotalNumberOfSpaces() {
		return this.space_array.length;
	}

	public double getTotalSquare() {
		double total_square = 0;
		for (Space e : this.space_array) {
			total_square += e.getSquare();
		}
		return total_square;
	}

	public int getTotalNumberOfRooms() {
		int total_number_of_rooms = 0;
		for (Space e : this.space_array) {
			total_number_of_rooms += e.getNumberOfRooms();
		}
		return total_number_of_rooms;
	}

	public Space[] getSpacesArray() {
        /*
		 Flat[] new_flats_array = new Flat[this.space_array.length];
		 for(int i=0; i < new_flats_array.length; i++){
		 new_flats_array[i]=new Flat(this.space_array[i].getNumberOfRooms(), this.space_array[i].getSquare());
		 }
		 return new_flats_array;
*/
		return this.space_array;
	}

	//получение квартиры по её номеру на этаже
	public Space getSpace(int number) {
		if (number < 0 || number >= this.space_array.length) {
			throw new SpaceIndexOutOfBoundsException();
		} else {
			//return new Flat(this.flats_array[i].getNumberOfRooms(),this.flats_array[i].getSquare());
			return this.space_array[number];
		}

	}

	//изменение квартиры по её номеру на этаже и ссылке на новую квартиру
	public void setSpace(int number_of_flat, Space new_flat) {
		if (number_of_flat < 0 || (number_of_flat > this.space_array.length - 1)) {
			throw new SpaceIndexOutOfBoundsException();
		} else {
			this.space_array[number_of_flat].setSquare(new_flat.getSquare());
			this.space_array[number_of_flat].setNumberOfRooms(new_flat.getNumberOfRooms());
		}
	}

	//добавление новый квартиры на этаж по будущему номеру
	public void addSpace(int new_number, Space new_flat) {
		if (new_number > this.space_array.length || new_number < 0) { //так как пропусков не должно быть
			throw new SpaceIndexOutOfBoundsException("wrong new index");
		} else {
			Space[] new_flats_array = new Space[this.space_array.length + 1];
			for (int i = 0; i < new_flats_array.length; i++) {
				if (i < new_number) {
					new_flats_array[i] = this.space_array[i];
				}
				if (new_number == i) {
					//new_flats_array[i]= new Flat(new_flat.getNumbersOfRooms(), new_flat.getSquare());
					new_flats_array[i] = new_flat;
				}
				if (i > new_number) {
					new_flats_array[i] = this.space_array[i - 1];
				}
			}
			this.space_array = new_flats_array;
		}

	}

	//удаление квартиры по её номеру на этаже
	public void dellSpace(int number) {
		if (number < 0 || number > this.space_array.length - 1) {
			throw new SpaceIndexOutOfBoundsException();
		} else {
			Space[] new_flats_array = new Space[this.space_array.length - 1];
			for (int i = 0, j = 0; i < this.space_array.length; i++, j++) {
				if (i < number) {
					new_flats_array[j] = this.space_array[i];
				}
				if (i == number) {
					j--;
				}
				if (i > number) {
					new_flats_array[j] = this.space_array[i];
				}
			}
			this.space_array = new_flats_array;
		}
	}

	//получение самой большой по площади квартиры
	public Space getBestSpace() {
		double best_space = 0;
		int number_best_space_flat = 0;
		for (int i = 0; i < this.space_array.length; i++) {
			if (best_space < this.space_array[i].getSquare()) {
				best_space = this.space_array[i].getSquare();
				number_best_space_flat = i;
			}
		}
		return this.space_array[number_best_space_flat];
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("DwellingFloor ");
		sb.append("(" + this.space_array.length);
		for (Space f : this.space_array) {
			sb.append(", " + f);
		}
		sb.append(")");
		return sb.toString();
	}

	public boolean equals(Object other_object) {
		if (this == other_object) return true;
		if (other_object == null) return false;
		if (this.getClass() != other_object.getClass()) return false;
		DwellingFloor other_dwelling_floor = (DwellingFloor) other_object;
		if (this.getTotalNumberOfSpaces() != other_dwelling_floor.getTotalNumberOfSpaces()) return false;
		for (int i = 0; i < this.getTotalNumberOfSpaces(); i++) {
			if (!this.getSpace(i).equals(other_dwelling_floor.getSpace(i))) return false;
		}
		return true;
	}

	public int hashCode() {
		int dwelling_floor_marker = 11;
		int result = this.getTotalNumberOfSpaces();
		for (int i = 0; i < this.getTotalNumberOfSpaces(); i++) {
			result = result ^ this.space_array[i].hashCode();
		}
		return dwelling_floor_marker * result;
	}

	public Object clone(){
		Space[] array = new Space[this.getTotalNumberOfSpaces()];
		for(int i=0; i < array.length; i++){
			array[i] = (Space) this.space_array[i].clone();
		}
		return new DwellingFloor(array);
	}


	@Override
	public Iterator<Space> iterator() {
		return new Iterator<Space>() {
			int count = 0;

			@Override
			public boolean hasNext() {
				if (count < space_array.length) return true;
				else return false;
			}

			@Override
			public Space next() {
				if (count >= space_array.length) throw new NoSuchElementException();
				if (!hasNext()) throw new NoSuchElementException();
				else{
					return space_array[count++];
				}
			}

		};
	}

	@Override
	public int compareTo(Floor o) {
		if(this.getTotalNumberOfSpaces() == o.getTotalNumberOfSpaces()) {
			return 0;
		}
		else if (this.getTotalNumberOfSpaces() < o.getTotalNumberOfSpaces()){
			return -1;
		}
		else return 1;
	}
}
