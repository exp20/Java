package t1.utilities;
import t1.buildings.interfaces.*;
import t1.exceptions.InexchangeableFloorsException;
import t1.exceptions.InexchangeableSpacesException;

public class PlacementExchanger {
    //Метод проверки возможности обмена помещениями.
    public static boolean checkSpaceExchanger(Space space1, Space space2){
        if (space1.getSquare() == space2.getSquare() && space1.getNumberOfRooms() == space2.getNumberOfRooms()){
            return true;
        }
        else{
            return false;
        }
    }

    //Метод проверки возможности обмена этажами.
    public static boolean checkFloorExchanger(Floor floor1, Floor floor2){
        if(floor1.getTotalSquare() == floor2.getTotalSquare() && floor1.getTotalNumberOfSpaces() == floor2.getTotalNumberOfSpaces()){
            return true;
        }
        else {
            return false;
        }
    }
    //Метод обмена помещениями двух этажей
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException{
        Space space1 = floor1.getSpace(index1), space2 = floor2.getSpace(index2);
        if (checkSpaceExchanger(space1, space2)){
            floor1.setSpace(index1,space2);
            floor2.setSpace(index2,space1);
        }
        else{
            throw new InexchangeableSpacesException();
        }
    }

    //Метод обмена этажами двух зданий
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException{
        Floor floor1 = building1.getFloor(index1), floor2 = building2.getFloor(index2);
        if(checkFloorExchanger(floor1,floor2)){
            building1.setFloor(index1,floor2);
            building2.setFloor(index2,floor1);
        }
        else{
            throw new InexchangeableFloorsException();
        }
    }
}
