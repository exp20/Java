package t1.utilities;


import t1.buildings.SynhronizedFloor;
import t1.buildings.dwelling.Dwelling;
import t1.buildings.dwelling.DwellingFloor;
import t1.buildings.dwelling.Flat;
import t1.buildings.interfaces.Building;
import t1.buildings.interfaces.BuildingFactory;
import t1.buildings.interfaces.Floor;
import t1.buildings.interfaces.Space;
import t1.utilities.factories.DwellingFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.util.Scanner;


//класс Buildings, работающий со ссылками типа Space, Floor, Building, содержащий статические методы ввода-вывода зданий
public class Buildings {
    private static BuildingFactory factory = new DwellingFactory(); //по умолчанию

    public void setBuildingFactory(BuildingFactory new_factory){
        factory = new_factory;
    }

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
                buff_space_arr[j]= factory.createSpace(d_in.readInt(),d_in.readDouble());
            }
            floors_array[i]= factory.createFloor(buff_space_arr);
        }
        return factory.createBuilding(floors_array);
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
                spaces_array[j]= factory.createSpace(rooms_numb,square);
            }
            floors_array[i]= factory.createFloor(spaces_array);
        }
    return factory.createBuilding(floors_array);
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
                spaces_array[j] = factory.createSpace(scanner.nextInt(), scanner.nextDouble());
            }
            floors_array[i]=factory.createFloor(spaces_array);
        }
        return factory.createBuilding(floors_array);
    }


    public static Space createSpace(double area) {
        return factory.createSpace(area);
    }

    //Создание объектов с помощью механизма рефликсии
    public static Space createSpace(Class<? extends Space> spaceClass, double area){ //ограничение типа: в метод может быть переданн Class
        // который реализует(наследует) интерфейс(класс) Space
        try {
            //return spaceClass.getConstructor( double.class ).newInstance( area );
            Constructor constructor = spaceClass.getConstructor(double.class);
            return (Space) constructor.newInstance(area);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException| InvocationTargetException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Space createSpace(int roomsCount, double area) {
        return factory.createSpace(roomsCount,area);
    }

    public static Space createSpace(Class<? extends Space> spaceClass, int roomsCount, double area) {
        try {
            return spaceClass.getConstructor(int.class, double.class).newInstance(roomsCount, area);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Floor createFloor(int spacesCount) {
        return factory.createFloor(spacesCount);
    }

    public static Floor createFloor(Class<? extends Floor> floorClass, int spacesCount) {
        try {
            return floorClass.getConstructor(int.class).newInstance(spacesCount);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Floor createFloor(Space[] spaces) {
        return factory.createFloor(spaces);
    }

    public static Floor createFloor(Class<? extends Floor> floorClass, Space[] spaces) {
        try {

            return floorClass.getConstructor(spaces.getClass()).newInstance( (Object) spaces); // (OBJECT) Это происходит потому ,
            // что newInstance(Object...) объявлен с переменным числом аргументов Object.
            // Поскольку массивы ковариантны, a Space[]также является Object[] и argArray интерпретируется как все аргументы вместо первого аргумента .
            //также работает new Obect[]{spaces}
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return factory.createBuilding(floorsCount, spacesCounts);
    }

    public static Building createBuilding(Class<? extends Building> buildingClass, int floorsCount, int[] spacesCounts){
        try {
            return buildingClass.getConstructor(int.class, spacesCounts.getClass()).newInstance(floorsCount, (Object) spacesCounts);
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Building createBuilding(Floor[] floors) {
        return factory.createBuilding(floors);
    }

    public static Building createBuilding(Class<? extends Building> buildingClass, Floor[] floors){
        try{
            return buildingClass.getConstructor(floors.getClass()).newInstance(new Object[] {floors});
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void sortUp() {//TODO
    }
    public static void sortDown () { // TODO
    }

    public static Floor synchronizedFloor (Floor floor){ //возвращающего ссылку на оболочку указанного объекта этажа, безопасную с точки зрения многопоточности.
        return new SynhronizedFloor(floor);
    }


}
