package main.java.util;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class BruteForce {
	public static Set<String> start(HashMap<String, Set<String>> rules){
		StringBuilder sb = new StringBuilder();
		
		for(String rule : rules.keySet()){
			sb.append(rule);
		}
		
		//Get all combinations
		Set<String> allCombination = BruteForce.combine(sb.toString());
		Set<String> results = new LinkedHashSet<>();
		
		//Filter the good combinations
		for(String item : allCombination){
			if(RuleUtil.isValid(item, rules)){
				results.add(item);
			}
		}
		return results;
	}
	
	private static Set<String> combine(String chars) {
	    Set<String> set = new TreeSet<String>();
	    if(chars.length() == 1) {
	    	set.add(chars);
	    } else {
	    	for (int i=0; i<chars.length(); i++) {
	    		String character = String.valueOf(chars.charAt(i));
	    		String remaining = chars.replace(character, "");

	    		for(String permutation : combine(remaining)) {
	    			set.add(character + permutation);
	    		}
	    	}
	    }
	    return set;
	}
}
