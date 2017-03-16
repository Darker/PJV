/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import cz.cvut.fel.pjv.Lab02;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mareda
 */
public class TestLab02 extends HomeworkTestBase {
    
    public TestLab02() {
        super();
    }

    @Override
    protected void runHW() {
        new Lab02().start(new String[] {});
    }
    protected static final String END = "End of input detected!\n";
    protected static final String LINE_ERR = "A number has not been parsed from line %d\n";
    protected static final String LINE_RESULT = "%2d %.3f %.3f\n";
    public static String lineErr(final int line) {
        return String.format(LINE_ERR, line);
    }
    public static String res(final int count, final double mean, final double deviation) {
        return String.format(LINE_RESULT, count, mean, deviation);
    }
    
    @Test
    public void Test10Vals() throws Exception {
        testHWBasic("1\n2\n3\n4\n5\n6\n7\n8\n9\n10", res(10, 5.500, 2.872)+END);
    }
    
    @Test
    public void TestNothing() throws Exception {
        testHWBasic("", END);
    }
    @Test
    public void TestEmptyLines() throws Exception {
        testHWBasic(" \n \n", END);
    }
    @Test
    public void TestErrors() throws Exception {
        testHWBasic(" \n \n5", lineErr(1)+lineErr(2)+END);
    }
    @Test
    public void NoOutput() throws Exception {
        testHWBasic("5", END);
    }
    @Test
    public void ShortOutput() throws Exception {
        testHWBasic("5\n5", END+res(2, 5.00, 0));
    }
    @Test
    public void Infinity() throws Exception {
        testHWBasic("Infinity\n5", lineErr(1)+END);
    }
    @Test
    public void NaN() throws Exception {
        testHWBasic("NaN\n5", lineErr(1)+END);
    }
    
    @Test
    public void MoreOutputs() throws Exception {
        testHWBasic("5\n5\n5\n5\n5\n"
                +   "5\n5\n5\n5\n5\n"
                +   "5\n5\n5\n5\n5\nb", res(10, 5, 0)+lineErr(16)+END+res(5, 5, 0));
    }
    
}
