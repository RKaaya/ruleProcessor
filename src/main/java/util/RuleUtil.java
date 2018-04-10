package main.java.util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class RuleUtil {
	public static boolean isValid(String s, HashMap<String, Set<String>> rules){
		boolean result = true;
		
		outerloop:
		for(String character : rules.keySet()) {
			for(String rule : rules.get(character)) {
				if(s.indexOf(rule) >= s.indexOf(character)) {
					result = false;
					break outerloop;
				}
			}
		}
		return result;
	}
	
	public static boolean isValid(List<String> characters, HashMap<String, Set<String>> rules) {
		StringBuilder sb = new StringBuilder();
		for(String character : characters){
			sb.append(character);
		}
		return isValid(sb.toString(), rules);
	}
	
	public static boolean isLoop(HashMap<String, Set<String>> rules, String visited, String current) {
		boolean result = false;
		if(visited.contains(current)){
	    	result = true;
	    } else {
	    	visited = visited.concat(current);
	    }
		Set<String>	currentRule = rules.get(current);
		
		if(currentRule.size() > 0 && !result){
			for(String rule : currentRule){
				result = isLoop(rules, visited, rule);
			}
		}
	    return result;
	  }
}
