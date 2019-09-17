package t1.buildings;

import t1.buildings.interfaces.Floor;
import t1.buildings.interfaces.Space;

import java.util.Iterator;

public class SynhronizedFloor implements Floor {
    private Floor floor;

    public SynhronizedFloor(Floor floor){
        this.floor = floor;
    }
    @Override
    public int getTotalNumberOfSpaces() {
        synchronized (floor){
            return floor.getTotalNumberOfSpaces();
        }
    }

    @Override
    public double getTotalSquare() {
        synchronized (floor){
          return floor.getTotalSquare();
        }
    }

    @Override
    public int getTotalNumberOfRooms() {
        synchronized (floor){
            return floor.getTotalNumberOfRooms();
        }
    }

    @Override
    public Space[] getSpacesArray() {
        synchronized (floor){
            return floor.getSpacesArray();
        }
    }

    @Override
    public Space getSpace(int index) {
        synchronized (floor){
            return floor.getSpace(index);
        }
    }

    @Override
    public void setSpace(int index, Space new_space) {
        synchronized (floor){
            floor.setSpace(index,new_space);
        }
    }

    @Override
    public void addSpace(int new_index, Space new_space) {
        synchronized (floor){
            floor.addSpace(new_index,new_space);
        }
    }

    @Override
    public void dellSpace(int dell_index) {
        synchronized (floor){
            floor.dellSpace(dell_index);
        }
    }

    @Override
    public Space getBestSpace() {
        synchronized (floor){
            return floor.getBestSpace();
        }
    }

    @Override
    public Object clone() {
        synchronized (floor){
            return floor.clone();
        }
    }

    @Override
    public int compareTo(Floor o) {
        synchronized (floor){
            return floor.compareTo(o);
        }
    }

    @Override
    public Iterator<Space> iterator() {
        synchronized (floor){
            return floor.iterator();
        }
    }

}
