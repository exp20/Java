package t1.buildings.office;

import t1.exceptions.*;

public class Office{
	private final int DEFAULT_NUMBER_OF_ROOMS=1;
	private final double DEFAULT_SQUARE=250;

	private int number_of_rooms;
	private double square;
	
	public Office(){
		this.number_of_rooms=DEFAULT_NUMBER_OF_ROOMS;
		this.square=DEFAULT_SQUARE;
	}

	public Office(double square){
		if (square<=0){
			throw new InvalidSpaceAreaException();
		}
		else{
			this.square = square;
		}
		this.number_of_rooms = DEFAULT_NUMBER_OF_ROOMS;
	}

	public Office(int number_of_rooms, double square){
		if(number_of_rooms <= 0){
			throw new InvalidRoomsCountException();
		}
		else
		{
			this.number_of_rooms=number_of_rooms;
			
		} 
		if (square <= 0){

			throw new InvalidSpaceAreaException();
		}
		else{
			this.square=square;
		}
		
	}

	public int getNumberOfRooms(){
		return this.number_of_rooms;
	}

	public void setNumberOfRooms(int new_number_of_rooms){
		if(new_number_of_rooms <= 0){
			throw new InvalidRoomsCountException();
		}
		else
		{
			this.number_of_rooms=new_number_of_rooms;
			
		} 
	}

	public double getSquare(){
		return this.square;
	}

	public void setSquare(double new_square){
		if (new_square <= 0){

			throw new InvalidSpaceAreaException();
		}
		else{
			this.square=new_square;
		}
	}

	public String toString(){
		return "Office ("+this.number_of_rooms+", "+this.square+")";
	}
	
}