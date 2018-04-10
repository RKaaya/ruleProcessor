package main.java.processCentral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import main.java.util.BruteForce;
import main.java.util.ListUtil;
import main.java.util.RuleUtil;

public class ProcessCentral {
	private static final String NO_SOLUTION = "No solution.";
	private final static Logger LOGGER = Logger.getLogger(ProcessCentral.class.getName());
	private HashMap<String, Set<String>> rules;
	
	public ProcessCentral(){
		rules = new HashMap<>();
	}
	
	//Make map with rules
	public void addRule(String rule) throws Exception{
		if(!rule.contains("=>")){
			throw new Exception("Invalid rule");
		}
		String[] processedRule = rule.split("=>");
		String firstCharacter = processedRule[0].trim();
		if(firstCharacter.length()!=1){
			throw new Exception("Invalid rule");
		}
		rules.putIfAbsent(firstCharacter, new HashSet<>());
		
		if(processedRule.length==2){
			String secondCharacter = processedRule[1].trim();
			if(secondCharacter.length()!=1){
				throw new Exception("Invalid rule");
			}
			rules.putIfAbsent(secondCharacter, new HashSet<String>());
			Set<String> currentRules = rules.get(firstCharacter);
			currentRules.add(secondCharacter);
		}
	}
	
	public String process(){
		StringBuilder sb = new StringBuilder();
		List<String> result = new ArrayList<>();
		for(String item : rules.keySet()){
			result.add(item);
		}
		
		//Check loops in rules
		boolean isLoop = isLoop(result);
		if(isLoop){
			sb.append(NO_SOLUTION);
			LOGGER.info(NO_SOLUTION);
		} else {
			//Call method to solve
			solveIt(result);
			for(String item : result){
				sb.append(item);
			}
			LOGGER.info("Solution is: " + sb.toString());
		}
		return sb.toString();
	}
	
	private boolean isLoop(List<String> result){
		boolean isLoop = false;
		for(String item : result){
			isLoop = RuleUtil.isLoop(rules, "", item);
			if(isLoop){
				break;
			}
		}
		return isLoop;
	}
	
	private void solveIt(List<String> result){
		while(!RuleUtil.isValid(result, rules)){
			for(String letter : rules.keySet()){
				for(String rule : rules.get(letter)){
					if(result.indexOf(rule) > result.indexOf(letter)){
						ListUtil.swapPosition(result, letter, rule);
					}
				}
			}
		}
	}
	
	//Solve with brute force
	public Set<String> bruteProcess() {
		Set<String> results = BruteForce.start(rules);
		if(results.isEmpty()){
			LOGGER.info(NO_SOLUTION);
		} else {
			LOGGER.info("Solution(s):");
			for(String result : results){
				LOGGER.info(result);
			}
		}
		return results;
	}
	
}
