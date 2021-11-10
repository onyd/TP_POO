package fr.ensimag.util;

import java.util.ArrayList;

/**
 * Represents an array with two dimension and a generic type
 *
 * @param <E>
 */
public class BiDimensionalArray<E> {
	private ArrayList<E> list;
	private int width;
	private int height;

	/**
	 * Create a bidimensional array
	 * 
	 * @param width
	 * @param height
	 */
	public BiDimensionalArray(int width, int height) {
		this.list = new ArrayList<>(width * height);
		for (int i = 0; i < width * height; i++) {
			this.list.add(null);
		}
		this.width = width;
		this.height = height;
	}

	/**
	 * Compute the index of the element at (i, j) in a circular manner
	 * 
	 * @param i
	 * @param j
	 * @return the index in the list
	 */
	private int indexOf(int i, int j) {
		// Circular behavior
		while (i < 0) {
			i += height;
		}
		while (j < 0) {
			j += width;
		}
		while (i >= height) {
			i -= height;
		}
		while (j >= width) {
			j -= width;
		}
		return i * width + j;
	}

	/**
	 * Get the element at (i, j)
	 * 
	 * @param i
	 * @param j
	 * @return the element
	 */
	public E get(int i, int j) {
		return list.get(indexOf(i, j));
	}
	
	/**
	 * Get the element at indicies
	 * @param indicies
	 * @return the element
	 */
	public E get(Pair<Integer, Integer> indicies) {
		return this.get(indicies.first, indicies.second);
	}
	
	/**
	 * Set the element at (i,j) by o
	 * @param i
	 * @param j
	 * @param o
	 */
	public void set(int i, int j, E o) {
		this.list.set(indexOf(i, j), o);
	}
	
	/**
	 * Set the element at indicies by o
	 * @param indicies
	 * @param o
	 */
	public void set(Pair<Integer, Integer> indicies, E o) {
		this.set(indicies.first, indicies.second, o);
	}
	
	/**
	 * Getter of the width of the array
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Getter of the height of the array
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

}
