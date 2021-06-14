/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Suite;

import bnf.ejercicio.BNFEjercicio1;
import bnf.ejercicio.BNFEjercicio2;
import bnf.ejercicio.BNFEjercicio3;
import bnf.ejercicio.BNFEjercicio4;
import bnf.ejercicio.BNFEjercicio5;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author LenovoF
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({BNFEjercicio1.class,BNFEjercicio2.class,BNFEjercicio3.class,BNFEjercicio4.class,BNFEjercicio5.class})
public class TestSuiteMain {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
