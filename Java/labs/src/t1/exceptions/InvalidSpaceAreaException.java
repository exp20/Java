//Ошибка некорретной площади помещения
package t1.exceptions;
public class InvalidSpaceAreaException extends IllegalArgumentException{
	public InvalidSpaceAreaException(){}

	public InvalidSpaceAreaException(String str){
	super(str);
	}
}