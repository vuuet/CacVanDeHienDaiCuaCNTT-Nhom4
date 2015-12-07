package test;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<String>{
	Map<String, Float> map;

	public ValueComparator(Map<String, Float> base) {
		this.map = base;
	}

	public int compare(String a, String b) {
		if (map.get(a) >= map.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}
