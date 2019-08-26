/**
Работа класса основана на двусвязном циклическом списке этажей с выделенной головой.
*/
package t1.buildings.office;

import t1.buildings.office.*;
import t1.exceptions.*;

public class OfficeBuilding{
	private Node head = null;
	private int number_of_elements=0;

		//элемент циклического сиска двусвязного
	private class Node{
		private Node nextElement = null;
		private Node prevElement = null;
		private OfficeFloor data = null;

		 Node(){}

		 Node( OfficeFloor office_floor){
			this.data = new OfficeFloor(office_floor.getNumberOfOffices());
			for(int i=0; i < office_floor.getNumberOfOffices(); i++){
				this.data.setOffice(i,office_floor.getOfficeByNumber(i));
			}
		}

		void setNextLink(Node next_link){
			this.nextElement=next_link;
		}
		void setPrevLink(Node prev_link){
			this.prevElement=prev_link;
		}

		OfficeFloor getData(){
			return this.data;
		}
		Node getNextLink(){
			return this.nextElement;
		}
		Node getPrevLink(){
			return this.prevElement;
		}

		void setData(OfficeFloor new_data){
			this.data = new_data;
		}
	}


	//Конструктор может принимать количество этажей и массив количества офисов по этажам.
	public OfficeBuilding (int number_of_floors, int[] numb_of_offices){
		this.head = new Node();
		for (int i =0; i < number_of_floors; i++){
			this.addNode(i, new Node(new OfficeFloor(numb_of_offices[i])));
		}
	}
	//Конструктор может принимать массив этажей офисного здания.
	public OfficeBuilding (OfficeFloor[] office_floor_array){
		this.head = new Node();
		for(int i=0; i < office_floor_array.length; i++){
			this.addNode(i,new Node (new OfficeFloor(office_floor_array[i].getOfficesArray())));
		}
	}

	//метод получения общего количества этажей здания.
	public int getNumberOfFloors(){
		return this.number_of_elements;
	}

	public int getTotalNumberOfOffices(){
		int tot_numb = 0;
		Node buff = this.head.getNextLink();
		do{
			tot_numb+=buff.getData().getNumberOfOffices();
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
	public OfficeFloor[] getOfficeFloorsArray(){
		OfficeFloor[] off_array = new OfficeFloor[this.number_of_elements];
		Node buff = this.head.getNextLink();
		int i =0;
		do{
			off_array[i]=buff.getData();
			buff=buff.getNextLink();
			i++;
		}
		while(buff!=this.head);
		return off_array;
	}

	//метод получения объекта этажа, по его номеру в здании.
	public OfficeFloor getOfficeFloor(int number){
		if (this.number_of_elements <= number || number < 0){
			throw new FloorIndexOutOfBoundsException();
		}
		else
		{
		return this.getNode(number, this.head).getData();
		}
	}

	//метод изменения этажа по его номеру в здании и ссылке на обновленный этаж
	public void setOfficeFloor(int index, OfficeFloor new_office_floor){
		if(index < 0 || index > this.number_of_elements-1){
			throw new FloorIndexOutOfBoundsException();
		}
		else{
		Office[] array_off = new_office_floor.getOfficesArray();
		Node ch_floor= getNode(index, this.head);
		ch_floor.setData(new OfficeFloor(array_off));
		}
	}

	//метод получения объекта офиса по его номеру в офисном здании
	public Office getOfficeByNumber(int number_of_office){
		if (this.getTotalNumberOfOffices() <= number_of_office || number_of_office < 0){
			throw new SpaceIndexOutOfBoundsException();
		}
		else{
		int[] off_indexes=this.getOfficeIndex(number_of_office);
		return this.getOfficeFloor(off_indexes[0]).getOfficeByNumber(off_indexes[1]);
		}
	}

	//метод изменения объекта офиса по его номеру в доме и ссылке типа офиса.
	public void setOffice(int number_of_office, Office new_office){
		if (this.getTotalNumberOfOffices() <= number_of_office || number_of_office < 0){
			throw new SpaceIndexOutOfBoundsException();
		}
		else{
			Office set_office = this.getOfficeByNumber(number_of_office);
			set_office.setNumberOfRooms(new_office.getNumberOfRooms());
			set_office.setSquare(new_office.getSquare());
		}
	}

	//метод добавления офиса в здание по номеру офиса в здании и ссылке на офис
	public void addOffice(int new_index, Office new_office){
		if (new_index > this.getTotalNumberOfOffices() || new_index < 0){
			throw new SpaceIndexOutOfBoundsException("wrong new index");
		}
		else
		{
			int buff_index = new_index;
			Node floor_buff = this.head.getNextLink();
			int buff_numb_floor_offices =0;
			for (int i =0; i < this.number_of_elements; i++){
				buff_numb_floor_offices = floor_buff.getData().getNumberOfOffices();
				if (buff_index<=buff_numb_floor_offices){
				this.getOfficeFloor(i).addOfficeToFloor(buff_index, new_office);
				return;
			}
			else{
				buff_index-=floor_buff.getData().getNumberOfOffices();
				floor_buff=floor_buff.getNextLink();
			}
			}

		}
	}

	//метод получения самого большого по площади офиса
	public Office getBestSpace(){
		if (this.number_of_elements==0){
			return null;
		}
		if (this.number_of_elements ==1){
			return this.getOfficeFloor(0).getBestSpace();

		}
		Office buff_best_space=this.head.getNextLink().getData().getBestSpace();
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
	public Office[] getSortArray(){
		Office[] sort_office_array= new Office[this.getTotalNumberOfOffices()];
		int k = 0;
		Office [] buff_arr;
		for(int i=0; i<this.number_of_elements; i++){
			buff_arr = this.getOfficeFloor(i).getOfficesArray();
			for (int j=0; j < buff_arr.length; j++){
				sort_office_array[k]=new Office(buff_arr[j].getNumberOfRooms(), buff_arr[j].getSquare());
				k+=1;
			}
		}	
		
		//сама сортировка

		for(int i=0; i < sort_office_array.length; i++){

			for (int j = i+1; j< sort_office_array.length; j++){
				if (sort_office_array[i].getSquare()<sort_office_array[j].getSquare()){
					Office buff = sort_office_array[i];
					sort_office_array[i]=sort_office_array[j];
					sort_office_array[j]=buff;
				}
			}
		}

		return sort_office_array;
	}

	//метод удаления офиса по его номеру в здании.
	public void dellOffice(int dell_numb){
		if (this.getTotalNumberOfOffices() <= dell_numb || dell_numb < 0){
			throw new SpaceIndexOutOfBoundsException();
		}
		else{
			int[] office_dell_index=this.getOfficeIndex(dell_numb);
			this.getOfficeFloor(office_dell_index[0]).dellOffice(office_dell_index[1]);
		}
	} 

	//метод получения номера этажа на котором расположена квартира по номеру и номера квартиры относительно этого этажа
	private int[] getOfficeIndex(int number_of_office){
			int buff_index = number_of_office;
			Node floor_buff = this.head.getNextLink();
			int buff_numb_floor_offices =0;
			for (int i =0; i < this.number_of_elements; i++){
				buff_numb_floor_offices = floor_buff.getData().getNumberOfOffices();
				if (buff_index<=buff_numb_floor_offices-1){
				return new int[]{i,buff_index};
			}
			else{
				buff_index-=floor_buff.getData().getNumberOfOffices();
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
		StringBuilder sb = new StringBuilder("OfficeBuilding ("+this.getNumberOfFloors());
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