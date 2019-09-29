package EE.RMI.client;


import EE.RMI.ImageProcessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {
    public static void main(String ... args){
        String input = args[0], output = args[1];
        try {
            File img_input = new File("./EE/RMI/"+input);
            BufferedImage input_read = ImageIO.read(img_input);
            /*
            WritableRaster raster = input_read.getRaster();
            DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
            byte [] img_bytes = dataBuffer.getData();*/

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(input_read, "jpg", baos);
            baos.flush();
           byte [] imageInByte = baos.toByteArray();
           baos.close();

            //получаем ищем сервис на сервере на порту 8199
            Registry registry = LocateRegistry.getRegistry(8199);


            ImageProcessing img_proc = (ImageProcessing) registry.lookup("server.ImgProcessing");

            byte[] result = img_proc.ImgProcess(imageInByte);
            System.out.println(result.length);
            //запись в файл картинки
            ByteArrayInputStream byteArrayInputStream  = new ByteArrayInputStream(result);
            BufferedImage buff_out_img = ImageIO.read(byteArrayInputStream);
            ImageIO.write(buff_out_img,"jpg",new File("./EE/RMI/"+output));
            System.out.println("PFFFF");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
