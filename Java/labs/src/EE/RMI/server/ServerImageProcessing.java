package EE.RMI.server;



import EE.RMI.ImageProcessing;
import EE.RMI.server.ImgHandler;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/*Разработать клиент-серверную программу, реализующую обработку изображений.

Клиентская программа получает в качестве параметров имена входного и выходного файлов изображений.
Для простоты рекомендуется для входных файлов использовать формат JPG, а для выходных PNG.
Далее клиентская программа вызывает удаленный метод обработки изображения указанным в варианте задания фильтром,
пересылая в качестве параметра файл с исходным изображением. После чего выходное изображение возвращается на клиентский компьютер как результат работы метода.*/
public class ServerImageProcessing {
    public static void main(String... args) {

        try {
            //создание объекта для удаленного доступа
            final ImageProcessing service =  new ImgHandler();

            //создание реестра  объетов

            final Registry registry = LocateRegistry.createRegistry(8199);

            //создание "заглушки" – приемника удаленных вызовов
            ImageProcessing stub = (ImageProcessing) UnicastRemoteObject.exportObject(service, 0);
            //регистрация "заглушки" в реесте
            registry.bind("server.ImgProcessing", stub);

            //усыпляем главный поток, иначе программа завершится
            Thread.sleep(Integer.MAX_VALUE);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
