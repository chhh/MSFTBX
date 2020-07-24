package com.dmtavt.msftbx;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chhh
 */
public class DmtavtTestTest {
    
    public DmtavtTestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getString method, of class DmtavtTest.
     */
    @Test
    public void testGetString() {
        System.out.println("getString");
        String expResult = "hoho";
        String result = DmtavtTest.getString();
        assertEquals(expResult, result);
    }
    
}
