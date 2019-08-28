// интерфейс описывающий функц этажа
package t1.buildings.interfaces;

public interface Floor{
	int getTotalNumberOfSpaces();

	double getTotalSquare();

	int getTotalNumberOfRooms();

	Space[] getSpacesArray();

	Space getSpace(int index);

	void setSpace(int index, Space new_space);

	void addSpace (int new_index, Space new_space);

	void dellSpace (int dell_index);
	
	Space getBestSpace();
}