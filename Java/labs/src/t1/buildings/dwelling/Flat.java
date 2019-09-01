/**
Класс предсталящий квартиру жилого дома которая имеет площадь и количество комнат
*/
package t1.buildings.dwelling;
import t1.exceptions.*;
import t1.buildings.interfaces.Space;

import java.io.Serializable;
import java.util.function.LongToIntFunction;

public class Flat implements Space, Serializable, Cloneable {
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

	public boolean equals(Object other_object){
		if(this==other_object) return true;
		if(other_object==null) return false;
		if(this.getClass()!=other_object.getClass()) return false;
		Flat other_flat = (Flat) other_object;
		return this.square==other_flat.getSquare() && this.numbers_of_rooms==other_flat.getNumberOfRooms();
	}

	public int hashCode(){
		int flat_marler = 3;
		long b0 = Double.doubleToLongBits(this.square);
		int b1=(int)(b0 >>> 32);
	    b0 = (b0 << 32);
	    int b00 = (int)(b0 >>> 32);
		return flat_marler*b1^b00^this.numbers_of_rooms;
	}

	public Object clone(){
		return new Flat(this.numbers_of_rooms,this.square);
	}
}