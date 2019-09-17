//интерфейс  помещения здания.
//соответствetn общей функциональности Flat и Office
package t1.buildings.interfaces;

public interface Space extends Comparable<Space>{
	int getNumberOfRooms();

	void setNumberOfRooms(int new_number_of_rooms);

	double getSquare();

	void setSquare(double new_square);

	boolean equals(Object obj);

	int hashCode();

	Object clone();
}