package main.java.util;

import java.util.List;

public class ListUtil {
	public static void swapPosition(List<String> list, String firstValue, String secondValue){
		int firstPosition = list.indexOf(firstValue);
		int secondPosition = list.indexOf(secondValue);
		if(firstPosition >= 0 && secondPosition >= 0) {
			list.set(firstPosition,secondValue);
			list.set(secondPosition,firstValue);
		}
	}
}
