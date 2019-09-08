package t1.buildings.interfaces;

import t1.buildings.interfaces.Space;
import t1.buildings.interfaces.Floor;

public interface Building extends Iterable<Floor> {

    int getTotalNumberOfFloors();

    int getTotalNumberOfSpaces();

    double getTotalSquare();

    int getTotalNumberOfRooms();

    Floor[] getFloorsArray();

    Floor getFloor(int index);

    void setFloor(int index, Floor new_floor);

    Space getSpace(int index);

    void setSpace(int index, Space new_space);

    void addSpace(int new_index, Space new_space);

    void dellSpace(int index);

    Space getBestSpace();

    Space[] getSortSpacesArray();

    boolean equals(Object obj);

    int hashCode();

    Object clone();
}