/**
Класс предсталящий квартиру жилого дома которая имеет площадь и количество комнат
*/
package t1.buildings.dwelling;

public class Flat{
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
		this.square=square;
	}
	public Flat(int numbers_of_rooms, double square){
		this.numbers_of_rooms=numbers_of_rooms;
		this.square=square;
	}
	public int getNumberOfRooms(){
		return this.numbers_of_rooms;
	}
	public void setNumberOfRooms(int new_numbers_of_rooms){
		this.numbers_of_rooms=new_numbers_of_rooms;
	}
	public double getSquare(){
		return this.square;
	}
	public void setSquare(double new_square){
		this.square=new_square;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("Flat");
		sb.append(" ("+this.numbers_of_rooms+", "+this.square+")");
		return sb.toString();
	}
}