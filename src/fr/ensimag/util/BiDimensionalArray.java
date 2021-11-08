package fr.ensimag.util;

import java.util.ArrayList;

public class BiDimensionalArray<E> {
	private ArrayList<E> list;
	private int width;
	private int height;

	public BiDimensionalArray(int width, int height) {
		this.list = new ArrayList<>(width * height);
		for (int i = 0; i < width * height; i++) {
			this.list.add(null);
		}
		this.width = width;
		this.height = height;
	}

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

	public E get(int i, int j) {
		return list.get(indexOf(i, j));
	}
	
	public E get(Pair<Integer, Integer> indicies) {
		return this.get(indicies.first, indicies.second);
	}
	
	public void set(int i, int j, E e) {
		this.list.set(indexOf(i, j), e);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
