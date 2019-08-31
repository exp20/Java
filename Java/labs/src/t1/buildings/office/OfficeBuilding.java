/**
Работа класса основана на двусвязном циклическом списке этажей с выделенной головой.
*/
package t1.buildings.office;

import t1.buildings.interfaces.Building;
import t1.buildings.office.*;
import t1.exceptions.*;
import t1.buildings.interfaces.*;

import java.io.Serializable;

public class OfficeBuilding implements Building, Serializable {
	private Node head = null;
	private int number_of_elements=0;

		//элемент циклического сиска двусвязного
	private class Node implements Serializable{
		private Node nextElement = null;
		private Node prevElement = null;
		private Floor data = null;

		 Node(){}

		 Node( OfficeFloor office_floor){
			this.data = new OfficeFloor(office_floor.getTotalNumberOfSpaces());
			for(int i=0; i < office_floor.getTotalNumberOfSpaces(); i++){
				this.data.setSpace(i,office_floor.getSpace(i));
			}
		}
		Node(Floor floor) {
			this.data = floor;
		}

		void setNextLink(Node next_link){
			this.nextElement=next_link;
		}
		void setPrevLink(Node prev_link){
			this.prevElement=prev_link;
		}

		Floor getData(){
			return this.data;
		}
		Node getNextLink(){
			return this.nextElement;
		}
		Node getPrevLink(){
			return this.prevElement;
		}

		void setData(Floor new_data){
			this.data = new_data;
		}
	}


	//Конструктор может принимать количество этажей и массив количества офисов по этажам.
	public OfficeBuilding (int number_of_floors, int...numb_of_offices){
		this.head = new Node();
		for (int i =0; i < number_of_floors; i++){
			this.addNode(i, new Node(new OfficeFloor(numb_of_offices[i])));
		}
	}
	//Конструктор может принимать массив этажей
	public OfficeBuilding (Floor...floors_array){
		this.head = new Node();
		for(int i=0; i < floors_array.length; i++){
			this.addNode(i,new Node (floors_array[i]));
		}
	}

	//метод получения общего количества этажей здания.
	public int getTotalNumberOfFloors(){
		return this.number_of_elements;
	}

	public int getTotalNumberOfSpaces(){
		int tot_numb = 0;
		Node buff = this.head.getNextLink();
		do{
			tot_numb+=buff.getData().getTotalNumberOfSpaces();
			buff=buff.getNextLink();
		}
		while(buff!=this.head);
		return tot_numb;
	}

	//метод получения общей площади помещений здания.
	public double getTotalSquare(){
		double tot_square =0;
		Node buff = this.head.getNextLink();
		do{
			tot_square+=buff.getData().getTotalSquare();
			buff=buff.getNextLink();
		}
		while(buff!=this.head);
		return tot_square;
	}

	//метод получения общего количества комнат здания.
	public int getTotalNumberOfRooms(){
		int tot_numb = 0;
		Node buff = this.head.getNextLink();
		do{
			tot_numb+=buff.getData().getTotalNumberOfRooms();
			buff=buff.getNextLink();
		}
		while(buff!=this.head);
		return tot_numb;
	}

	//метод получения массива этажей офисного здания
	public Floor[] getFloorsArray(){
		Floor[] floors_array = new Floor[this.number_of_elements];
		Node buff = this.head.getNextLink();
		int i =0;
		do{
			floors_array[i]=buff.getData();
			buff=buff.getNextLink();
			i++;
		}
		while(buff!=this.head);
		return floors_array;
	}

	//метод получения объекта этажа, по его номеру в здании.
	public Floor getFloor(int number){
		if (this.number_of_elements <= number || number < 0){
			throw new FloorIndexOutOfBoundsException();
		}
		else
		{
		return this.getNode(number, this.head).getData();
		}
	}

	//метод изменения этажа по его номеру в здании и ссылке на обновленный этаж
	public void setFloor(int index, Floor new_floor){
		if(index < 0 || index > this.number_of_elements-1){
			throw new FloorIndexOutOfBoundsException();
		}
		else{;
		Node ch_floor= getNode(index, this.head);
		ch_floor.setData(new_floor);
		}
	}

	//метод получения объекта офиса по его номеру в офисном здании
	public Space getSpace(int number){
		if (this.getTotalNumberOfSpaces() <= number || number < 0){
			throw new SpaceIndexOutOfBoundsException();
		}
		else{
		int[] off_indexes=this.getOfficeIndex(number);
		return this.getFloor(off_indexes[0]).getSpace(off_indexes[1]);
		}
	}

	//метод изменения объекта офиса по его номеру в доме и ссылке типа офиса.
	public void setSpace(int number_of_space, Space new_space){
		if (this.getTotalNumberOfSpaces() <= number_of_space || number_of_space < 0){
			throw new SpaceIndexOutOfBoundsException();
		}
		else{
			Space set_space = this.getSpace(number_of_space);
			set_space.setNumberOfRooms(new_space.getNumberOfRooms());
			set_space.setSquare(new_space.getSquare());
		}
	}

	//метод добавления офиса в здание по номеру офиса в здании и ссылке на офис
	public void addSpace(int new_index, Space new_space){
		if (new_index > this.getTotalNumberOfSpaces() || new_index < 0){
			throw new SpaceIndexOutOfBoundsException("wrong new index");
		}
		else
		{
			int buff_index = new_index;
			Node floor_buff = this.head.getNextLink();
			int buff_numb_floor_offices = 0;
			for (int i =0; i < this.number_of_elements; i++){
				buff_numb_floor_offices = floor_buff.getData().getTotalNumberOfSpaces();
				if (buff_index<=buff_numb_floor_offices){
				this.getFloor(i).addSpace(buff_index, new_space);
				return;
			}
			else{
				buff_index-=floor_buff.getData().getTotalNumberOfSpaces();
				floor_buff=floor_buff.getNextLink();
			}
			}

		}
	}

	//метод получения самого большого по площади офиса
	public Space getBestSpace(){
		if (this.number_of_elements==0){
			return null;
		}
		if (this.number_of_elements ==1){
			return this.getFloor(0).getBestSpace();

		}
		Space buff_best_space=this.head.getNextLink().getData().getBestSpace();
		Node buff_node=this.head.getNextLink();
		double best_space = buff_best_space.getSquare();
		do{
			if(best_space<buff_node.getData().getBestSpace().getSquare()){
				best_space=buff_node.getData().getBestSpace().getSquare();
				buff_best_space = buff_node.getData().getBestSpace();
			}
			buff_node=buff_node.getNextLink();
		}
		while (buff_node!=this.head);
		return buff_best_space;
		}

	//метод получения отсортированного по убыванию площадей массива офисов.
	public Space[] getSortSpacesArray(){
		Space[] sort_spaces_array= new Space[this.getTotalNumberOfSpaces()];
		int k = 0;
		Space [] buff_arr;
		for(int i=0; i<this.number_of_elements; i++){
			buff_arr = this.getFloor(i).getSpacesArray();
			for (int j=0; j < buff_arr.length; j++){
				sort_spaces_array[k]= buff_arr[j];
				k+=1;
			}
		}	
		
		//сама сортировка

		for(int i=0; i < sort_spaces_array.length; i++){

			for (int j = i+1; j< sort_spaces_array.length; j++){
				if (sort_spaces_array[i].getSquare()<sort_spaces_array[j].getSquare()){
					Space buff = sort_spaces_array[i];
					sort_spaces_array[i]=sort_spaces_array[j];
					sort_spaces_array[j]=buff;
				}
			}
		}

		return sort_spaces_array;
	}

	//метод удаления офиса по его номеру в здании.
	public void dellSpace(int dell_numb){
		if (this.getTotalNumberOfSpaces() <= dell_numb || dell_numb < 0){
			throw new SpaceIndexOutOfBoundsException();
		}
		else{
			int[] office_dell_index=this.getOfficeIndex(dell_numb);
			this.getFloor(office_dell_index[0]).dellSpace(office_dell_index[1]);
		}
	} 

	//метод получения номера этажа на котором расположена квартира по номеру и номера квартиры относительно этого этажа
	private int[] getOfficeIndex(int number_of_office){
			int buff_index = number_of_office;
			Node floor_buff = this.head.getNextLink();
			int buff_numb_floor_offices =0;
			for (int i =0; i < this.number_of_elements; i++){
				buff_numb_floor_offices = floor_buff.getData().getTotalNumberOfSpaces();
				if (buff_index<=buff_numb_floor_offices-1){
				return new int[]{i,buff_index};
			}
			else{
				buff_index-=floor_buff.getData().getTotalNumberOfSpaces();
				floor_buff=floor_buff.getNextLink();
				}
			}

		return null;
	}


	private Node getNode(int number, Node next_elem){
		if(number == 0 ){ //хвост рекурсии, при этом next_elem указывает на head
		return next_elem.getNextLink();
		}
		else{
			return getNode(--number, next_elem.getNextLink());
		}
				
	}

	private void addNode(int new_element_index, Node new_element){
		Node new_node = new Node (new_element.getData());
		if (this.number_of_elements==0){ //вставка в начало
			this.head.setNextLink(new_node);
			new_node.setNextLink(this.head);
			this.head.setPrevLink(new_node);
			new_node.setPrevLink(this.head);
			this.number_of_elements++;
		}
		else{ //вставка в середину или в конец
			if (new_element_index == this.number_of_elements){
				Node prev_node = getNode(new_element_index-1,this.head);
				prev_node.setNextLink(new_node);
				new_node.setNextLink(this.head);
				new_node.setPrevLink(prev_node);
				this.number_of_elements++;
			}
			else { //В середину
				Node node_before_new = getNode(new_element_index-1,this.head);
				Node node_after_new=node_before_new.getNextLink();
				new_node.setNextLink(node_after_new);
				new_node.setPrevLink(node_before_new);
				node_before_new.setNextLink(new_node);
				node_after_new.setPrevLink(new_node);
				this.number_of_elements++;
				}
				
			}
	}

	// метод удаленя узла по номеру в списке
	private void dellNode(int index_of_dell_element){
		Node dell_node = getNode(index_of_dell_element,this.head);
		Node prev_node = dell_node.getPrevLink();
		Node next_node = dell_node.getNextLink();
		prev_node.setNextLink(next_node);
		next_node.setPrevLink(prev_node);
		this.number_of_elements--;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder("OfficeBuilding ("+this.getTotalNumberOfFloors());
		Node buff = this.head.getNextLink();
		do{
			sb.append(", "+buff.getData());
			buff = buff.getNextLink();
		}
		while(buff!=this.head);
		sb.append(")");
	return sb.toString();
	}
}