package t1.exceptions;

import java.io.IOException;

//Метод оценки стоимости в случае ареста здания должен выбросить объявляемое исключение
public class BuildingUnderArrestException extends IOException {
    public BuildingUnderArrestException(){
        super();
    }
}
