/**
Класс предсталящий квартиру жилого дома которая имеет площадь и количество комнат
*/
package t1.buildings.dwelling;
import t1.exceptions.*;
import t1.buildings.interfaces.Space;

public class Flat implements Space {
	private final int DEF_NUMBERS_OF_ROOMS=2;
	private final double DEF_SQUARE=50;
	private int numbers_of_rooms;
	private double square;

	public Flat(){
		this.numbers_of_rooms=DEF_NUMBERS_OF_ROOMS;
		this.square=DEF_SQUARE;
	}
	public Flat(double square){
		
		this.numbers_of_rooms=DEF_NUMBERS_OF_ROOMS;
		if (square<=0){
			throw new InvalidSpaceAreaException("wrong area square");
		}
		else{
		this.square=square;
		}
	}
	public Flat(int numbers_of_rooms, double square){
		if (numbers_of_rooms<=0){
			throw new InvalidRoomsCountException("wrong numbers of rooms");
		}
		else{
			this.numbers_of_rooms=numbers_of_rooms;
		}
		if (square<=0){
			throw new InvalidSpaceAreaException("wrong area square");
		}
		else{
		this.square=square;
		}
	}
	public int getNumberOfRooms(){
		return this.numbers_of_rooms;
	}
	public void setNumberOfRooms(int new_numbers_of_rooms){
		if (new_numbers_of_rooms<=0){
			throw new InvalidRoomsCountException("wrong numbers of rooms");
		}
		else{
			this.numbers_of_rooms=new_numbers_of_rooms;
		}
	}
	public double getSquare(){
		return this.square;
	}
	public void setSquare(double new_square){
		if (new_square<=0){
			throw new InvalidSpaceAreaException("wrong area square");
		}
		else{
		this.square=new_square;
		}
		
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("Flat");
		sb.append(" ("+this.numbers_of_rooms+", "+this.square+")");
		return sb.toString();
	}
}