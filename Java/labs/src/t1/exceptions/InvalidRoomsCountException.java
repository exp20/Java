//Ошибка некорретного количества комнат в помещении
package t1.exceptions;
public class InvalidRoomsCountException extends IllegalArgumentException{
	InvalidRoomsCountException(){}

	InvalidRoomsCountException(String str){
		super(str);
	}
}