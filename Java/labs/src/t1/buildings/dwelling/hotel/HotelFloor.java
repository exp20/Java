package t1.buildings.dwelling.hotel;

import t1.buildings.dwelling.DwellingFloor;
import t1.buildings.interfaces.Floor;
import t1.buildings.interfaces.Space;

public class HotelFloor extends DwellingFloor implements Floor {
    private static int DEFAULT_STARS_COUNT=1;
    private int stars_count;
    public HotelFloor(int spaces_count){
        super(spaces_count);
        this.stars_count=this.DEFAULT_STARS_COUNT;
    }
    public HotelFloor(Space... spaces_array){
        super(spaces_array);
        this.stars_count=this.DEFAULT_STARS_COUNT;
    }
    public void set_stars(int new_stars){
        this.stars_count=new_stars;
    }
    public int get_stars_count(){
        return this.stars_count;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("HotelFloor ("+this.get_stars_count()+", "+this.getTotalNumberOfSpaces());
        for(int i = 0; i < this.getTotalNumberOfSpaces(); i++) {
            sb.append(", "+this.getSpace(i));
        }
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(Object other_object){
        if(other_object == null) return false;
        if(this == other_object) return true;
        if(this.getClass() != other_object.getClass()) return false;
        HotelFloor other_hotel_floor =  (HotelFloor) other_object;
        if(this.get_stars_count()!=other_hotel_floor.get_stars_count()){
            return false;
        }
        if(this.getTotalNumberOfSpaces()!= other_hotel_floor.getTotalNumberOfSpaces()){
            return false;
        }
        else{
            for(int i =0; i < this.getTotalNumberOfSpaces(); i++){
                if(!this.getSpace(i).equals(other_hotel_floor.getSpace(i))){
                    return false;
                }
            }
        }
        return true;
    }
    public int hashCode(){
        int result = super.hashCode();
        return result^this.get_stars_count()^this.getTotalNumberOfSpaces();
    }
}
