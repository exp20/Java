package t1.exceptions;
//несоответствие обменивающихся этажей
public class InexchangeableFloorsException extends Exception{
    public InexchangeableFloorsException(){
        super();
    }
    public InexchangeableFloorsException(String s){
        super(s);
    }
}
