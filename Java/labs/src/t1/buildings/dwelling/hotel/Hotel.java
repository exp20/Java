package t1.buildings.dwelling.hotel;

import t1.buildings.dwelling.Dwelling;
import t1.buildings.interfaces.Floor;
import t1.buildings.interfaces.Space;

public class Hotel extends Dwelling {
    public Hotel(int number_of_floors, int... number_spaces_at_floors){
        super(number_of_floors,number_spaces_at_floors);
    }
    public Hotel(Floor... floors_array){
        super(floors_array);
    }
    public int get_stars_count(){
        int buff = 0;
        for(int i =0; i < this.getTotalNumberOfFloors(); i++){
            Floor buff_floor = this.getFloor(i);
            if(buff_floor instanceof HotelFloor){
                if(((HotelFloor) buff_floor).get_stars_count()>buff){
                    buff = ((HotelFloor) buff_floor).get_stars_count();
                }
            }
        }
        return buff;
    }
    public Space getBestSpace(){
        Space buff_best_space = null;
        double buff_best_space_score=0;
        for(int i = 0; i < this.getTotalNumberOfFloors(); i++){
            Floor buff_floor = this.getFloor(i);
            Space best_space_at_buff_floor = buff_floor.getBestSpace();
            double buff_score = best_space_at_buff_floor.getSquare();
            if(buff_floor instanceof HotelFloor){
                switch (((HotelFloor) buff_floor).get_stars_count()){
                    case 1: buff_score*=0.25; break;
                    case 2: buff_score*=0.5; break;
                    case 3: buff_score*=1; break;
                    case 4: buff_score*=1.25; break;
                    case 5: buff_score*=1.5; break;
                }
                if(buff_score>buff_best_space_score){
                    buff_best_space_score=buff_score;
                    buff_best_space = best_space_at_buff_floor;
                }
            }
            //этаж не HotelFloor
            else{
                if(buff_score > buff_best_space_score){
                    buff_best_space = best_space_at_buff_floor;
                    buff_best_space_score = buff_score;
                }
            }
        }
        return buff_best_space;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("HotelBuilding ("+ this.get_stars_count()+", " + this.getTotalNumberOfFloors());
        for(int i =0; i < this.getTotalNumberOfFloors(); i++){
            sb.append(", "+this.getFloor(i));
        }
        sb.append(")");
        return sb.toString();
}
    public boolean equals(Object other_object){
        if(this == other_object) return true;
        if(other_object == null) return false;
        if(this.getClass()!=other_object.getClass()){
            return false;
        }
        Hotel other_hotel  = (Hotel) other_object;
        if(this.getTotalNumberOfFloors()!=other_hotel.getTotalNumberOfFloors()){
            return false;
        }
        else{
            for(int i=0; i < this.getTotalNumberOfFloors(); i++){
                if(!this.getFloor(i).equals(other_hotel.getFloor(i))){
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode(){
        return super.hashCode()^this.getTotalNumberOfFloors();
    }

}

