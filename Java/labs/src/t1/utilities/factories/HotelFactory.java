package t1.utilities.factories;

import t1.buildings.dwelling.Flat;
import t1.buildings.dwelling.hotel.Hotel;
import t1.buildings.dwelling.hotel.HotelFloor;
import t1.buildings.interfaces.Building;
import t1.buildings.interfaces.BuildingFactory;
import t1.buildings.interfaces.Floor;
import t1.buildings.interfaces.Space;

public class HotelFactory implements BuildingFactory {
    @Override
    public Space createSpace(double area) {
        return new Flat(area);
    }

    @Override
    public Space createSpace(int roomsCount, double area) {
        return new Flat(roomsCount, area);
    }

    @Override
    public Floor createFloor(int spacesCount) {
        return new HotelFloor(spacesCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new HotelFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new Hotel(floorsCount, spacesCounts);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Hotel(floors);
    }
}
