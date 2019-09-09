// интерфейс описывающий функц этажа
package t1.buildings.interfaces;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public interface Floor extends Iterable<Space>, Comparable<Floor> {
	int getTotalNumberOfSpaces();

	double getTotalSquare();

	int getTotalNumberOfRooms();

	Space[] getSpacesArray();

	Space getSpace(int index);

	void setSpace(int index, Space new_space);

	void addSpace (int new_index, Space new_space);

	void dellSpace (int dell_index);
	
	Space getBestSpace();

	boolean equals (Object obj);

	int hashCode();

	Object clone();

}