/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bnf.ejercicio;

import static bnf.ejercicio.BNFEjercicio4.be;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LenovoF
 */
public class BNFEjercicio2 {
    static BNFEjercicio be = new BNFEjercicio();
    public BNFEjercicio2() {
            
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

    @Test
    /**
     * Test of main method, of class BNFEjercicio.
     */
     public void testMain() throws FileNotFoundException {
        System.out.println("Test2");
        String folder = "Documents";
        String[] args = {folder+"Entrada\\BNF_Entrada2.txt"};
        File file  = new File(folder+"\\Entrada\\Out2.txt");
        PrintStream printStream = new PrintStream(file);
        PrintStream standard = System.out;
        BufferedReader reader = new BufferedReader( new FileReader(folder + "Entrada\\Salida2.txt"));
        String line;
        ArrayList<String> SET_VALUES = new ArrayList<String>();        
        try {
            line = reader.readLine();
            while(line != null){
                SET_VALUES.add(line);
                
                line = reader.readLine();
           }
        } catch (IOException ex) {
            Logger.getLogger(BNFEjercicio4.class.getName()).log(Level.SEVERE, null, ex);
        }        
        Set<String> hash_Set;
        hash_Set = new HashSet<String>(SET_VALUES);        
        
        System.setOut(printStream);
        be.main(args);
        reader = new BufferedReader( new FileReader(folder+"Entrada\\Out2.txt"));
        int count = 0;
        try {
            line = reader.readLine();
            while(line != null){
                if(!hash_Set.contains((String)line)){
                    fail("Hay una palabra demas");
                }
                
                count ++;
                line = reader.readLine();
            }
            System.setOut(standard);            
            System.out.println(count+"/"+SET_VALUES.size());

            if(count < SET_VALUES.size())
                fail("Faltan palabras detectar."+count+"/"+SET_VALUES.size());
            if(count > SET_VALUES.size())
                fail("Se detectaron más palabras."+count+"/"+SET_VALUES.size());
        } catch (IOException ex) {
            Logger.getLogger(BNFEjercicio3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
