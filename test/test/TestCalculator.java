/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.Test;
import static org.junit.Assert.*;
import cz.cvut.fel.pjv.Lab01;

/**
 *
 * @author mareda
 */
public class TestCalculator extends HomeworkTestBase {
    
    public TestCalculator() {
    }
    /**
     * Shorthand for formated number output
     * @param number 
     * @param precision Number of decimal places to show.  Trailing zeros will appear to fully fill the number of requested places
     * @return 
     */
    protected static String forNum(double number, int precision) {
        final String precisionFormula = "%."+Integer.toString(precision)+"f";
        return String.format(precisionFormula, number);
    }
    protected static String formatResult(double A, String operator, double B, double result, int precision) {
        return forNum(A,precision)+" "+operator+" "+forNum(B,precision)+" = "+forNum(result,precision)+"\n";
    }
    
    @Override
    protected void runHW() {
        new Lab01().start(new String[] {});
    }
    
    protected static final String INTRO = "Vyber operaci (1-soucet, 2-rozdil, 3-soucin, 4-podil):\n";
    
    protected static final String SCITANEC = "Zadej scitanec: \n";
    protected static final String CINITEL = "Zadej cinitel: \n"; 
    protected static final String DELENEC = "Zadej delenec: \n";
    protected static final String DELITEL = "Zadej delitel: \n";
    protected static final String MENSENEC = "Zadej scitanec: \n";
    protected static final String MENSITEL = "Zadej scitanec: \n";
    
    protected static final String SCITANI = SCITANEC+SCITANEC;
    protected static final String NASOBENI = CINITEL+CINITEL;
    protected static final String ODCITANI = MENSENEC+MENSITEL;
    protected static final String DELENI = DELENEC+DELITEL;
    
    protected static final String ERR_DIVIDE_BY_ZERO = "Pokus o deleni nulou!\n";
    protected static final String ERR_INVALID_CHOICE = "Chybna volba!\n";
    protected static final String ERR_NEGATIVE_PRECISION = "Chyba - musi byt zadane kladne cislo!\n";
    
    protected static final String PRECISION = "Zadej pocet desetinnych mist: \n";
    
    
    
    @Test
    public void TestOnePlusOne() throws Exception {
        testHWBasic("1 1 1 1", INTRO + SCITANI+ PRECISION + formatResult(1, "+", 1, 2, 1));
    }
    @Test
    public void TestDivideByZero() throws Exception {
        testHWBasic("4 1 0 1", INTRO + DELENI + ERR_DIVIDE_BY_ZERO);
    }
    @Test
    public void TestIntDivide() throws Exception {
        testHWBasic("4 1 10 0", INTRO + DELENI + PRECISION + formatResult(1, "/", 10, 0, 0));
    }
    @Test
    public void TestInvalidChoice() throws Exception {
        testHWBasic("5 1 10 0", INTRO + ERR_INVALID_CHOICE);
    }
    
    @Test
    public void TestInvalidPrecision() throws Exception {
        testHWBasic("1 1 10 -5", INTRO + SCITANI + PRECISION + ERR_NEGATIVE_PRECISION);
    }
    
    @Test
    public void TestDoubleMultiply() throws Exception {
        double A = 12.345678;
        double B = 1.23456;
        testHWBasic("3 "+String.format("%f", A)+" "+String.format("%f", B)+" 3", INTRO + NASOBENI + PRECISION + formatResult(A, "*", B, 15.241, 3));
    }
}
