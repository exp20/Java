//Ошибка выхода за границы номеров помещений
package t1.exceptions;
public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException{
	public SpaceIndexOutOfBoundsException(){}

	public SpaceIndexOutOfBoundsException(String str){
		super(str);
	}
}