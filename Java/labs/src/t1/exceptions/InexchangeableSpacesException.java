package t1.exceptions;
//несоответствия обменивающихся помещений
public class InexchangeableSpacesException extends Exception{
    public InexchangeableSpacesException(){
        super();
    }
    public InexchangeableSpacesException(String s){
        super(s);
    }
}
