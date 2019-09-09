package t1.buildings.office;

import t1.buildings.dwelling.Flat;
import t1.exceptions.*;
import t1.buildings.interfaces.Space;

import java.io.Serializable;

public class Office implements Space, Serializable {
	private final int DEFAULT_NUMBER_OF_ROOMS = 1;
	private final double DEFAULT_SQUARE = 250;

	private int number_of_rooms;
	private double square;

	public Office() {
		this.number_of_rooms = DEFAULT_NUMBER_OF_ROOMS;
		this.square = DEFAULT_SQUARE;
	}

	public Office(double square) {
		if (square <= 0) {
			throw new InvalidSpaceAreaException();
		} else {
			this.square = square;
		}
		this.number_of_rooms = DEFAULT_NUMBER_OF_ROOMS;
	}

	public Office(int number_of_rooms, double square) {
		if (number_of_rooms <= 0) {
			throw new InvalidRoomsCountException();
		} else {
			this.number_of_rooms = number_of_rooms;

		}
		if (square <= 0) {

			throw new InvalidSpaceAreaException();
		} else {
			this.square = square;
		}

	}

	public int getNumberOfRooms() {
		return this.number_of_rooms;
	}

	public void setNumberOfRooms(int new_number_of_rooms) {
		if (new_number_of_rooms <= 0) {
			throw new InvalidRoomsCountException();
		} else {
			this.number_of_rooms = new_number_of_rooms;

		}
	}

	public double getSquare() {
		return this.square;
	}

	public void setSquare(double new_square) {
		if (new_square <= 0) {

			throw new InvalidSpaceAreaException();
		} else {
			this.square = new_square;
		}
	}

	public String toString() {
		return "Office (" + this.number_of_rooms + ", " + this.square + ")";
	}

	public boolean equals(Object object) {
		if (this == object) return true; // если передали туже самую ссылку
		if (object == null) return false;
		if (object.getClass() != this.getClass()) {
			return false;
		}
		Flat other_flat = (Flat) object;
		return this.square == other_flat.getSquare()
				&& this.number_of_rooms == other_flat.getNumberOfRooms();

	}

	public int hashCode() {
		int office_marker = 7;
		long b0 = Double.doubleToLongBits(this.square);
		int b1 = (int) (b0 >>> 32);
		b0 = (b0 << 32);
		int b00 = (int) (b0 >>> 32);
		return office_marker * b1 ^ b00 ^ this.number_of_rooms;
	}

	public Object clone() {
		return new Office(this.number_of_rooms, this.square);
	}

	@Override
	public int compareTo(Space o) {
		if (this.square == o.getSquare()) {
			return 0;
		} else if (this.square > o.getSquare()) {
			return 1;
		} else return -1;
	}
}