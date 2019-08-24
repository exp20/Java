//Ошибка выхода за границы номеров этажей
package t1.exceptions;
public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
	FloorIndexOutOfBoundsException(){}

	FloorIndexOutOfBoundsException(String str){
		super(str);
	}
}