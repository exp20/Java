package t1.buildings.comparators;

import t1.buildings.interfaces.Space;

import java.util.Comparator;

/*класс критерия, реализующий интерфейс java.util.Comparator таким образом,
чтобы он сравнивал помещения по количеству комнат и считал бОльшим помещение с меньшим количеством комнат.*/
public class RecerseCompatatorSpaces implements Comparator<Space> {

    @Override
    public int compare(Space o1, Space o2) {
        if(o1.getNumberOfRooms() == o2.getNumberOfRooms()){
            return 0;
        }
        else{
            return (o1.getNumberOfRooms() > o2.getNumberOfRooms()) ? -1 : 1;
        }
    }
}
