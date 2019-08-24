//Ошибка некорретной площади помещения
package t1.exceptions;
public class InvalidSpaceAreaException extends IllegalArgumentException{
	InvalidSpaceAreaException(){}

	InvalidSpaceAreaException(String str){
	super(str);
	}
}