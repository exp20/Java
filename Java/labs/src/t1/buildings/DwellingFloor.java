/**
Класс описывающий этаж жилого здания
*/
package t1.buildings;

import t1.buildings.Flat;

public class DwellingFloor{
	private Flat[] flats_array;

	public DwellingFloor(int number_of_flats){
		this.flats_array = new Flat[number_of_flats];
		for(int i=0; i < number_of_flats; i++){
			flats_array[i]=new Flat();
		}
	}

	public DwellingFloor(Flat[] new_flats_array){
		this(new_flats_array.length);
		for (int i=0; i < new_flats_array.length; i++){
			this.flats_array[i].setNumberOfRooms(new_flats_array[i].getNumberOfRooms());
			this.flats_array[i].setSquare(new_flats_array[i].getSquare());
		}
	}

	public int getNumbersOfFlats(){
		return this.flats_array.length;
	}

	public double getTotalSquare(){
		double total_square=0;
		for(Flat e:this.flats_array){
			total_square+=e.getSquare();
		}
		return total_square;
	}

	public int getTotalNumbersOfRooms(){
		int total_number_of_rooms=0;
		for(Flat e: this.flats_array){
			total_number_of_rooms+=e.getNumberOfRooms();
		}
		return total_number_of_rooms;
	}

	public Flat[] getFlatsArray(){
		/**
		Flat[] new_flats_array = new Flat[this.flats_array.length];
		for(int i=0; i < flats_array.length; i++){
			new_flats_array[i]=new Flat(this.flats_array[i].getNumberOfRooms(), this.flats_array[i].getSquare());
		}
		return new_flats_array;
		*/
		return this.flats_array;
	}
	//получение квартиры по её номеру на этаже
	public Flat getFlatByNumber(int number){
		if(number < 0 || number>this.flats_array.length){
		System.out.println(" getFlatByNumber: wrong flat number"); 
		return null;
		}
		else{
			//return new Flat(this.flats_array[i].getNumberOfRooms(),this.flats_array[i].getSquare());
			return this.flats_array[number];
		}
		
	}
	//изменение квартиры по её номеру на этаже и ссылке на новую квартиру 
	public void setFlat(int number_of_flat,Flat new_flat){
		if(number_of_flat < 0 || number_of_flat>this.flats_array.length-1){
		 System.out.println(" setFlat: wrong flat number"); 
		return;
		}
		else{
			this.flats_array[number_of_flat].setSquare(new_flat.getSquare());
			this.flats_array[number_of_flat].setNumberOfRooms(new_flat.getNumberOfRooms());
			}
	}
	
	//добавление новый квартиры на этаж по будущему номеру
	public void addFlatToFloor(int new_number, Flat new_flat){
		if (new_number>this.flats_array.length || new_number<0){ //так как пропусков не должно быть
			System.out.println("Error: addFlatToFloor (new number > flats_array or < 0 )"); 
			return;
		}
		else{
			Flat[] new_flats_array = new Flat[this.flats_array.length+1];
			for(int i=0; i < new_flats_array.length; i++){
				if (i<new_number){
					new_flats_array[i]= this.flats_array[i];
				}
				if (new_number==i){  
					//new_flats_array[i]= new Flat(new_flat.getNumbersOfRooms(), new_flat.getSquare());
					new_flats_array[i]=new_flat;
				}
				if (i>new_number){
					new_flats_array[i]=this.flats_array[i-1];
				}
			} 
			this.flats_array=new_flats_array;
		}
		
	}
	//удаление квартиры по её номеру на этаже
	public void dellFlat(int number){
		if(number<0||number>this.flats_array.length-1){
			System.out.println("dellFlat: wrong number");
		}
		else{
			Flat[] new_flats_array= new Flat[this.flats_array.length-1];
			for(int i=0, j=0; i<this.flats_array.length; i++, j++){
				if(i<number){
					new_flats_array[j]=this.flats_array[i];
				}
				if (i==number){
					j--;
				}
				if(i>number){
					new_flats_array[j]=this.flats_array[i];
				}
			}
			this.flats_array=new_flats_array;
		}
	}
	//получение самой большой по площади квартиры
	public Flat getBestSpace(){
		double best_space=0;
		int number_best_space_flat=0;
		for(int i=0; i < this.flats_array.length; i++){
			if (best_space< this.flats_array[i].getSquare()){
				best_space=this.flats_array[i].getSquare();
				number_best_space_flat=i;
			}
		}
		return this.flats_array[number_best_space_flat];
	}
	public String toString(){
		StringBuilder sb = new StringBuilder("DwellingFloor ");
		sb.append("("+this.flats_array.length);
		for (Flat f:this.flats_array){
			sb.append(", "+f);
		}
		sb.append(")");
		return sb.toString();
	}

}
