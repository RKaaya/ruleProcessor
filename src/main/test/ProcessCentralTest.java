package main.test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import main.java.processCentral.ProcessCentral;

public class ProcessCentralTest {

	@Test
	public void processCentralAddRule() throws Exception {
		ProcessCentral mc = new ProcessCentral();
		mc.addRule("x =>");
		String result = mc.process();
		assertEquals(result, "x");
	}
	
	@Test
	public void processCentralAddRule2() throws Exception {
		ProcessCentral mc = new ProcessCentral();
		mc.addRule("x =>");
		mc.addRule("y =>");
		mc.addRule("z =>");
		String result = mc.process();
		assertEquals(result, "xyz");
	}
	
	@Test
	public void processCentralAddRule3() throws Exception {
		ProcessCentral mc = new ProcessCentral();
		mc.addRule("x =>");
		mc.addRule("y => z");
		mc.addRule("z =>");
		String result = mc.process();
		assertEquals(result, "xzy");
	}
	
	@Test
	public void processCentralAddRule4() throws Exception {
		ProcessCentral mc = new ProcessCentral();
		mc.addRule("u =>");
		mc.addRule("v => w");
		mc.addRule("w => z");
		mc.addRule("x => u");
		mc.addRule("y => v");
		mc.addRule("z =>");
		String result = mc.process();
		assertEquals(result, "uzwxvy");
	}

	@Test
	public void processCentralAddRule4Brute() throws Exception {
		ProcessCentral mc = new ProcessCentral();
		mc.addRule("u =>");
		mc.addRule("v => w");
		mc.addRule("w => z");
		mc.addRule("x => u");
		mc.addRule("y => v");
		mc.addRule("z =>");
		String result = mc.process();
		Set<String> bruteResult = mc.bruteProcess();
		assertTrue(bruteResult.contains(result));
	}
	
	@Test
	public void processCentralIsLoop() throws Exception {
		ProcessCentral mc = new ProcessCentral();
		mc.addRule("u =>");
		mc.addRule("v => w");
		mc.addRule("w => z");
		mc.addRule("x => u");
		mc.addRule("y => v");
		mc.addRule("z =>");
		mc.addRule("z => v");
		String result = mc.process();
		assertEquals(result, "No solution.");
	}
	
	@Test
	public void processCentralIsLoop2() throws Exception {
		ProcessCentral mc = new ProcessCentral();
		mc.addRule("u =>");
		mc.addRule("v => w");
		mc.addRule("w => z");
		mc.addRule("x => u");
		mc.addRule("y => v");
		mc.addRule("z =>");
		mc.addRule("u => u");
		String result = mc.process();
		assertEquals(result, "No solution.");
	}
	
	@Test(expected = Exception.class)
	public void processCentralException() throws Exception {
		ProcessCentral mc = new ProcessCentral();
		mc.addRule("u = d");
		String result = mc.process();
		assertEquals(result, "No solution.");
	}
}
