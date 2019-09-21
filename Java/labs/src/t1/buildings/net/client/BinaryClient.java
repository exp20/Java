package t1.buildings.net.client;

import t1.buildings.interfaces.Building;
import t1.buildings.interfaces.BuildingFactory;
import t1.utilities.Buildings;
import t1.utilities.factories.DwellingFactory;
import t1.utilities.factories.HotelFactory;
import t1.utilities.factories.OfficeFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/*Входными параметрами программы  являются имена трех файлов.
Первый файл существует на момент запуска программы и содержит в текстовом виде информацию о зданиях (например, одна строка – одно здание).
Второй файл существует на момент запуска программы и содержит в текстовом виде информацию о видах зданий (например, одна строка – одно слово,
определяющее вид здания).
Количество записей в первом и втором файле можно считать соответствующим друг другу,
но неизвестным заранее (т.е. оно даже не записано в первой строке файлов). Файлы можно считать корректными.
Третий файл должен быть создан программой в ходе работы и должен хранить
в текстовом виде оценки стоимости зданий (например, одна строчка – одна оценка стоимости).*/

/*Программа должна установить через сокеты соединение с сервером,
после чего считывать из первого и второго файла данные о здании,
передавать их в бинарной форме серверу и получать от него результат оценки стоимости здания,
и так по очереди для всех исходных данных.*/

public class BinaryClient {

    private static int SERVER_PORT = 8090;

    public static void main(String ... args){
        if (args.length!=3){
            System.out.println("Wrong input parameters");
            return;
        }
        File build_info = new File(args[0]);
        File build_type = new File(args[1]);
        File build_cost = new File(args[2]);

        if(!build_info.exists()){
            System.out.println(build_info.getName()+" not exist");
            return;
        }
        if(!build_type.exists()){
            System.out.println(build_type.getName()+ " not exist");
            return;
        }
        try {
            build_cost.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", SERVER_PORT), 5000);
            DataInputStream s_in = new DataInputStream(socket.getInputStream());
            DataOutputStream s_out = new DataOutputStream(socket.getOutputStream());

            BufferedReader build_info_reader = new BufferedReader(new FileReader(build_info));
            BufferedReader build_type_reader = new BufferedReader(new FileReader(build_type));
            BufferedWriter build_cost_writer = new BufferedWriter(new FileWriter(build_cost));

            String building_type;

            DwellingFactory dwelling_factory = new DwellingFactory();
            OfficeFactory office_factory = new OfficeFactory();
            HotelFactory hotel_factory = new HotelFactory();
            Buildings builds_util = new Buildings();

            while ((building_type = build_type_reader.readLine())!=null){
                switch (building_type){
                    case "Dwelling":
                        builds_util.setBuildingFactory(dwelling_factory);
                        break;
                    case "Office":
                        builds_util.setBuildingFactory(office_factory);
                        break;
                    case "Hotel":
                        builds_util.setBuildingFactory(hotel_factory);
                        break;
                }
                Building building = Buildings.readBuilding(new StringReader(build_info_reader.readLine()));
                System.out.println("Считали "+building);

                //Отправка данных на сервер
                s_out.writeUTF(building_type);
                s_out.flush();
                Buildings.outputBuilding(building, s_out);
                s_out.flush();
                System.out.println("Отправка информации о здании");

                // Считывание ответа от сервера
                String building_cost = s_in.readUTF();
                build_cost_writer.write(building_cost);
                build_cost_writer.write("\n");
            }

            System.out.println("Вся информация отправлена отключение клиента ...");
            s_out.writeUTF("END");
            build_info_reader.close();
            build_type_reader.close();
            build_cost_writer.close();

            s_in.close();
            s_out.close();
            socket.close();
            return;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
