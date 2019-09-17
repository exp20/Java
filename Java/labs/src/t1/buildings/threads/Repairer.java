package t1.buildings.threads;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;
import t1.buildings.interfaces.Floor;

//нить последовательно считывает значения площадей помещений этажа.
// Каждый раз, когда она читает значение площади помещения, она выводит в консоль сообщение
//По достижении конца этажа, а также в случае прерывания  нить заканчивает свое выполнение.
public class Repairer extends Thread{
    private Floor floor;
    public Repairer(Floor floor){
        this.floor = floor;
    }
    public void run() {
        try {
            for (int i = 0; i < this.floor.getTotalNumberOfSpaces(); i++) {
                System.out.printf("\n Repairing space number %d with total area %f square meters", i, this.floor.getSpace(i).getSquare());
                Thread.sleep(1);
            }
        }
        //исключение может вылезти если попытаться прервать блокированный поток
        // Проверка состояние прерывания вызовом метода isInterrupted() не обяз и не удобна.
        //так как если метод sleep() вызывается когда установлено состояние прерывания то состояние потока очищается и генерируется исключение
        catch (InterruptedException e){
            //ничего не меняет, но состояние потока может быть проверено в вызывающей части программы
            Thread.currentThread().interrupt();
        }
        System.out.println(" Поток "+Thread.currentThread().getName()+" закончил выполнение");
    }

}
