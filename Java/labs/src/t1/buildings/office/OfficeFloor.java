package t1.buildings.office;

import t1.buildings.office.*;
import t1.exceptions.*;
import t1.buildings.interfaces.*;
/**
	работа класса основана на односвязном циклическом списке офисов с выделенной головой.
	*/
public class OfficeFloor implements Floor{

	private ListElement head=null;
	private int number_of_elements=0;

	//элемент списка
private class ListElement{
	private ListElement next_element=null;
	private Space data=null;

	ListElement(){}
		
	ListElement (Space off){
		this.data = off;
		}

	Space getData(){
		return this.data;
		}

	void setData(Space new_data){
		this.data.setNumberOfRooms(new_data.getNumberOfRooms());
		this.data.setSquare(new_data.getSquare());
		}

	ListElement getNextLink(){
		return this.next_element;
		}

	void setNextLink(ListElement next_elem){
		this.next_element=next_elem;
		}
	}

	//принимает кол-во оффисов
	public OfficeFloor(int number_of_offices){
		this.head = new ListElement();
		this.head.setNextLink(this.head);
		for (int i=0; i < number_of_offices; i++){
			this.addElement(i, new ListElement(new Office()));
		}
	}
	
	//принимает массив
	public OfficeFloor(Space...spaces_array){
		this.head = new ListElement();
		this.head.setNextLink(this.head);
		for (int i=0; i < spaces_array.length; i++){
			this.addElement(i, new ListElement(spaces_array[i]));
		}
	}

	// метод полчение элемента по его номеру в списке
	//организуем рекурсию
	private ListElement getElement(int number_of_element, ListElement next_elem){	
				if(number_of_element == 0 ){ //хвост рекурсии, при этом next_elem указывает на head
					return next_elem.getNextLink();
				}
				else{
					return getElement(--number_of_element, next_elem.getNextLink());
				}
				
	}
	//метод добавления узла в список по номеру.
	private void addElement(int new_element_index, ListElement new_element){
		//вставка в начало при пустом списке
			if (this.number_of_elements==0 || new_element_index==0){
				ListElement buff = new ListElement(new_element.getData());
				buff.setNextLink(this.head.getNextLink());
				this.head.setNextLink(buff); //зацикливание списка
				this.number_of_elements++;
			}
			else{ //добавление в середину или в конец
				if (new_element_index == this.number_of_elements){
					ListElement buffer = getElement(new_element_index-1,this.head);
					ListElement buffer2 = new ListElement(new_element.getData());
					buffer2.setNextLink(this.head);
					buffer.setNextLink(buffer2);
					this.number_of_elements++;
				}
				else { //В середину
					System.out.println("добавляем "+ new_element_index);
					ListElement elem_before_new = getElement(new_element_index-1,this.head);
					ListElement newElem = new ListElement(new_element.getData());
					newElem.setNextLink(elem_before_new.getNextLink());
					elem_before_new.setNextLink(newElem);
					this.number_of_elements++;
				}
					
			}
	}

	// метод удаленя узла по номеру в списке
	private void dellElement(int index_of_dell_element){
		
			if (index_of_dell_element==0){ //удаление из начала
				this.head.setNextLink(this.head.getNextLink().getNextLink());
				this.number_of_elements--;
			}
			else  // удаление из конца или середины
			{
				if(index_of_dell_element == this.number_of_elements-1){
					getElement(index_of_dell_element-1, this.head).setNextLink(this.head);
					this.number_of_elements--;
				}

				else { //удаление из середины
					ListElement elem_before_dell_elem = getElement(index_of_dell_element-1, this.head);
					ListElement elem_after_dell_elem = elem_before_dell_elem.getNextLink().getNextLink();
					elem_before_dell_elem.setNextLink(elem_after_dell_elem);
					this.number_of_elements--;
				}
			}
	}

	public int getTotalNumberOfSpaces(){
		return this.number_of_elements;
	}
	//метод получения общей площади помещений этажа.
	public double getTotalSquare(){
		if(this.number_of_elements==0){
			System.out.println("getTotalSpace: 0 offices "); 
			return 0;
		}
		else{
			double square=0;
			ListElement buffer=this.head.getNextLink();
			do{
				square += buffer.getData().getSquare();
				buffer=buffer.getNextLink();
			}
			while (buffer!=this.head);
			return square;
		}

	}

	//метод получения общего количества комнат этажа.
	public int getTotalNumberOfRooms(){
		if(this.number_of_elements==0){
			System.out.println("getTotalNumberOfRooms: 0 offices "); 
			return 0;
		}
		else{
			 int number=0;
			ListElement buffer=this.head.getNextLink();;
			do{
				number += buffer.getData().getNumberOfRooms();
				buffer=buffer.getNextLink();
			}
			while (buffer!=this.head);
			return number;
		}
	}
	// метод получения массива офисов этажа.
	public Space[] getSpacesArray(){
		if(this.number_of_elements==0){
			System.out.println("getOfficeArray: 0 offices "); 
			return null;
		}
		else{
			Space[] offices_array = new Space[this.number_of_elements];
			int i=0;
			ListElement buffer=this.head.getNextLink();
			do{
				offices_array[i]=buffer.getData();
				buffer=buffer.getNextLink();
				i++;
			}
			while (buffer!=this.head);
			return offices_array;
		}
	}

	//метод получения офиса по его номеру на этаже.
	public Space getSpace(int number){
		if (this.number_of_elements <= number ||  number < 0){
			throw new SpaceIndexOutOfBoundsException();
			}
		else
			{
		return this.getElement(number, this.head).getData();
			}
	}

	//метод изменения офиса по его номеру на этаже и ссылке на обновленный офис.
	public void setSpace(int number, Space new_office){
		if (this.number_of_elements <= number || number < 0){
			throw new SpaceIndexOutOfBoundsException();
			}
		else
			{
			Space buffer = getSpace(number);
			buffer.setNumberOfRooms(new_office.getNumberOfRooms());
			buffer.setSquare(new_office.getSquare());
			}
	}

	//метод добавления нового офиса на этаже по будущему номеру офиса.
	public void addSpace(int new_number, Space new_space){
		if (new_number > this.number_of_elements|| new_number<0){ //так как пропусков не должно быть
			throw new SpaceIndexOutOfBoundsException("wrong new index");
			}
		else
		{
			this.addElement(new_number, new ListElement(new_space));
		}
	}

	//метод удаления офиса по его номеру на этаже.
	public void dellSpace(int number){
		if (this.number_of_elements <= number|| number< 0){
			throw new SpaceIndexOutOfBoundsException();
			}
	else{
		this.dellElement(number);
		}
}
	//получения самого большого по площади офиса этажа
	public Space getBestSpace(){
		if(this.number_of_elements==0){
			System.out.println("getBestSpace: 0 offices "); 
			return null;
		}
		ListElement buffer=this.head.getNextLink();
		Space best_square_office=this.head.getNextLink().getData();
		do{
				if(best_square_office.getSquare()<buffer.getData().getSquare()){
					best_square_office=buffer.getData();
				}
				buffer=buffer.getNextLink();
			}
			while (buffer!=this.head);
			return best_square_office;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder("Office Floor ("+this.number_of_elements);
		if(number_of_elements > 0){
			ListElement buffer=this.head.getNextLink();
			do{	
				sb.append(", ");
				sb.append(buffer.getData());
				buffer=buffer.getNextLink();
			}
			while(buffer!=this.head);
			sb.append(")");
			return sb.toString();
	}
		return sb.toString();
	}

}	