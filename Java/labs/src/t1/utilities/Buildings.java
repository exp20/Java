package t1.utilities;


import t1.buildings.dwelling.Dwelling;
import t1.buildings.dwelling.DwellingFloor;
import t1.buildings.dwelling.Flat;
import t1.buildings.interfaces.Building;
import t1.buildings.interfaces.Floor;
import t1.buildings.interfaces.Space;
import java.io.*;
import java.util.Scanner;


//класс Buildings, работающий со ссылками типа Space, Floor, Building, содержащий статические методы ввода-вывода зданий
public class Buildings {
    //записи данных о здании в байтовый поток
    public static void outputBuilding (Building building, OutputStream out) throws IOException {
        DataOutputStream d_out = new DataOutputStream(new BufferedOutputStream(out));
        d_out.writeInt(building.getTotalNumberOfFloors());
        for(Floor floor : building.getFloorsArray()){
            d_out.writeInt(floor.getTotalNumberOfSpaces());
            for (Space space : floor.getSpacesArray()){
                d_out.writeInt(space.getNumberOfRooms());
                d_out.writeDouble(space.getSquare());
            }
        }
        d_out.flush();
    }

    //чтения данных о здании из байтового потока
    public static Building inputBuilding (InputStream in) throws IOException {
        DataInputStream d_in = new DataInputStream(new BufferedInputStream(in));
        Floor[] floors_array = new Floor[d_in.readInt()];
        for(int i=0; i < floors_array.length; i++){
            int spaces_number = d_in.readInt();
            Space[] buff_space_arr = new Space[spaces_number];
            for(int j =0 ; j < spaces_number; j++){
                buff_space_arr[j]=new Flat(d_in.readInt(),d_in.readDouble());
            }
            floors_array[i]=new DwellingFloor(buff_space_arr);
        }
        return new Dwelling(floors_array);
    }


    //записи здания в символьный поток
    public static void writeBuilding (Building building, Writer out){
        PrintWriter p_writer = new PrintWriter(new BufferedWriter(out));
        p_writer.print(building.getTotalNumberOfFloors());
        p_writer.print(' ');
        for(Floor floor : building.getFloorsArray()){
            p_writer.print(floor.getTotalNumberOfSpaces());
            p_writer.print(' ');
            for (Space space : floor.getSpacesArray()){
                p_writer.print(space.getNumberOfRooms());
                p_writer.print(' ');
                p_writer.print(space.getSquare());
                p_writer.print(' ');
            }
        }
        p_writer.flush();
    }
    //чтения здания из символьного потока
    public static Building readBuilding (Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        tokenizer.ordinaryChar(' ');
        int numb_of_floors = (int) tokenizer.nval;
        Floor[] floors_array = new Floor[numb_of_floors];
        for(int i =0; i < numb_of_floors; i++){
            Space[] spaces_array = new Space[(int) tokenizer.nval];
            for(int j =0; j < spaces_array.length; j++){
                int rooms_numb = (int) tokenizer.nval;
                double square = (double) tokenizer.nval;
                spaces_array[j]=new Flat(rooms_numb,square);
            }
            floors_array[i]= new DwellingFloor(spaces_array);
        }
    return new Dwelling(floors_array);
    }

    //сериализации зданий в байтовый поток
    public static void serializeBuilding (Building building, OutputStream out) throws IOException {
        ObjectOutputStream obj_out = new ObjectOutputStream(new BufferedOutputStream(out));
        obj_out.writeObject(building);
        obj_out.flush();
    }
    public static Building deserialaizeBuilding (InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream obj_input = new ObjectInputStream(new BufferedInputStream(in));
        return (Building) obj_input.readObject();
    }

    public static void writeBuildingFormat (Building building, Writer out){
        PrintWriter p_writer = new PrintWriter(out);
        p_writer.printf("%d ", building.getTotalNumberOfFloors());
        for(Floor floor : building.getFloorsArray()) {
            p_writer.printf("%d ", floor.getTotalNumberOfSpaces());
            for (Space space : floor.getSpacesArray()) {
                p_writer.printf("%d %.2f ", space.getNumberOfRooms(), space.getSquare());
            }
        }
        p_writer.flush();
    }

    public static Building readBuilding(Building building, Scanner scanner){
        Floor[] floors_array = new Floor[scanner.nextInt()];
        for(int i=0; i < floors_array.length; i++){
            Space[] spaces_array = new Space[scanner.nextInt()];
            for(int j=0; j < spaces_array.length; j++){
                spaces_array[j] = new Flat(scanner.nextInt(), scanner.nextDouble());
            }
            floors_array[i]=new DwellingFloor(spaces_array);
        }
        return new Dwelling(floors_array);
    }

}
