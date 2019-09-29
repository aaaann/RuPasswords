package com.example.rupasswords;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordsHelperTest {

    private final static String[] RUS = {"й", "ц", "у", "к", "е", "н"};
    private final static String[] LAT = {"q", "w", "e", "r", "t", "y"};

    private final static String[] SOURCE = {"йцукен", "йцуфыв"};
    private final static String[] RESULT = {"qwerty", "qweфыв"};

    private PasswordsHelper helper;


    @Before
    public void setUp() throws Exception {
        helper = new PasswordsHelper(RUS, LAT);
    }

    @Test
    public void convert() {
        //assertTrue("Error in test case", );
        assertEquals("",helper.convert(""));

        for (int  i = 0; i < SOURCE.length; i++){
            assertEquals(RESULT[i], helper.convert(SOURCE[i]));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertIsThrows() {
        new PasswordsHelper(RUS, new String[]{"f"});
    }

}