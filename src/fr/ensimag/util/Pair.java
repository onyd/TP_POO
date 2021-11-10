package fr.ensimag.util;

/**
 * Represent an immutable pair of two elements
 * @param <T1>
 * @param <T2>
 */
public class Pair<T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<Pair<T1, T2>>{
	/**
	 * first element of the pair
	 */
	public final T1 first;
	
	/**
	 * second element of the pair
	 */
	public final T2 second;
	
	/**
	 * Create a pair 
	 * @param first
	 * @param second
	 */
	public Pair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public int compareTo(Pair<T1, T2> p) {
		if (this.first.compareTo(p.first) < 0) {
			return -1;
		}
		else if (this.first.compareTo(first) > 0) {
			return 1;
		}
		else if (this.first.compareTo(p.first) == 0) {
			if (this.second.compareTo(p.second) < 0) {
				return -1;
			} else if (this.second.compareTo(p.second) > 0) {
				return 1;
			}
		}
		
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pair<?, ?>) {
			Pair<?, ?> p = (Pair<?, ?>) o;
			return this.first.equals(p.first) && this.second.equals(p.second);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
	
}
