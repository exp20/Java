//Ошибка некорретного количества комнат в помещении
package t1.exceptions;
public class InvalidRoomsCountException extends IllegalArgumentException{
	public InvalidRoomsCountException(){}

	public InvalidRoomsCountException(String str){
		super(str);
	}
}
