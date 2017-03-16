/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Before;
/**
 *
 * @author mareda
 */
public abstract class HomeworkTestBase {
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    // <3 Thanks to http://stackoverflow.com/q/1119385/607407 
    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    // sterr not used yet, but might be useful in future assignments
    protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();    

    /*
     @returns Output without \r characters which are added on Windows.
    */
    protected String sanitizedOutput() {
        return outContent.toString().replace("\r", "");
    }
    /**
     * Override this to execute homework assignment internal code (which generates std io)
     */
    protected abstract void runHW();
    
    protected void testHWBasic(String input, String expected_output) {
        simulateIn(input);
        // Homework assignment executes here
        runHW();
        assertEquals(expected_output, sanitizedOutput());
    }
    
    public static void simulateIn(final String str) {
        // Simulated STDIN
        final ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
        try {
            in.close();
        } catch (IOException ex) {
            // nope
        }
        System.setIn(in);
    }
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
        System.setIn(null);
    }
}
