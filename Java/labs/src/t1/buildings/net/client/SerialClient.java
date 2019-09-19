package t1.buildings.net.client;


import t1.buildings.interfaces.Building;
import t1.exceptions.BuildingUnderArrestException;
import t1.utilities.Buildings;
import t1.utilities.factories.DwellingFactory;
import t1.utilities.factories.HotelFactory;
import t1.utilities.factories.OfficeFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

// пересылка объектов
public class SerialClient {
    private static int SERVER_PORT = 8090;

    public static void main(String ... args) {
        if (args.length != 3) {
            System.out.println("Wrong input parameters");
            return;
        }
        File build_info = new File(args[0]);
        File build_type = new File(args[1]);
        File build_cost = new File(args[2]);

        if (!build_info.exists()) {
            System.out.println(build_info.getName() + " not exist");
            return;
        }
        if (!build_type.exists()) {
            System.out.println(build_type.getName() + " not exist");
            return;
        }
        try {
            build_cost.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("localhost", SERVER_PORT), 4000);
            ObjectOutputStream obj_out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream obj_in = new ObjectInputStream(socket.getInputStream());
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
                obj_out.writeObject(building_type);
                obj_out.flush();
                Buildings.serializeBuilding(building, obj_out);
                System.out.println("Отправка информации о здании");

                // Считывание ответа от сервера
                Object answer = obj_in.readObject();
                if (answer instanceof BuildingUnderArrestException) {
                    build_cost_writer.write("Arrested");
                }
                else {
                    Double cost = (Double) answer;
                    build_cost_writer.write(cost.toString());
                }

                build_cost_writer.write("\n");
            }

            System.out.println("Вся информация отправлена отключение клиента ...");
            String end = "END";
            obj_out.writeObject(end);
            build_info_reader.close();
            build_type_reader.close();
            build_cost_writer.close();

            obj_in.close();
            obj_out.close();
            socket.close();
            return;


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    }
