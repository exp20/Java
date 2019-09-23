package t1.buildings.net.server;

import t1.buildings.interfaces.Building;
import t1.exceptions.BuildingUnderArrestException;
import t1.utilities.Buildings;
import t1.utilities.factories.DwellingFactory;
import t1.utilities.factories.HotelFactory;
import t1.utilities.factories.OfficeFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class BinaryServer {
    private static int PORT = 8090;

    public static void main(String ... args){
        try {
            ServerSocket s = new ServerSocket(PORT);
            Socket incomm = s.accept();
            System.out.printf("Клиент %s подключился", incomm.toString());
            DataInputStream c_in = new DataInputStream(incomm.getInputStream());
            DataOutputStream c_out = new DataOutputStream(incomm.getOutputStream());
            while(true){
                String building_type = c_in.readUTF();
                Building building;
                Buildings build_util = new Buildings();
                System.out.println("Полученный тип "+building_type);
                switch (building_type){
                    case "Dwelling":
                        build_util.setBuildingFactory(new DwellingFactory());
                        break;
                    case "Office":
                        build_util.setBuildingFactory(new OfficeFactory());
                        break;
                    case "Hotel":
                        build_util.setBuildingFactory(new HotelFactory());
                        break;
                    case "END":
                        c_in.close();
                        c_out.close();
                        incomm.close();
                        s.close();
                        System.out.println("Завершение работы сервера ...");
                        return;
                }
                building = Buildings.inputBuilding(c_in);
                System.out.println("Полученно "+building);
                try {
                    c_out.writeUTF(Double.toString(getBuildingCost(building_type,building)));
                    System.out.println("Отправка стоимости");
                }
                catch (BuildingUnderArrestException e){
                    System.out.println("Здание под арестом");
                    c_out.writeUTF("Arrested");

                }

            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Оценка стоимости здания считается как сумма всех площадей помещений здания, умноженная на 1000$ для жилого дома,
    на 1500$ для офиса и на 2000$ для любой гостиницы.*/
    private static double getBuildingCost(String type, Building building) throws BuildingUnderArrestException {
        Random rnd = new Random();
        int rnd_rez = rnd.nextInt(10);
        if (rnd_rez <=2){
            throw new BuildingUnderArrestException();
        }
        int coeff = 0;
        switch (type){
            case "Dwelling":
                coeff = 1000;
                break;
            case "Office":
                coeff = 1500;
            case "Hotel":
                coeff = 2000;
                break;
        }
        return building.getTotalSquare()*coeff;
    }
}