//Ошибка выхода за границы номеров помещений
package t1.exceptions;
public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException{
	SpaceIndexOutOfBoundsException(){}

	SpaceIndexOutOfBoundsException(String str){
		super(str);
	}
}