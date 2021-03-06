/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bnf.ejercicio;

import static bnf.ejercicio.BNFEjercicio1.be;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
public class BNFEjercicio3 {
    static BNFEjercicio be = new BNFEjercicio();
    public BNFEjercicio3() {
            
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
     * Test of main method, of class BNFEjercicio.
     */
    @Test
    public void testMain() throws FileNotFoundException {
        System.out.println("Test3");
        String folder = "Documents";
        String[] args = {folder+"Entrada\\BNF_Entrada3.txt"};
        File file  = new File(folder+"\\Entrada\\Out3.txt");
        PrintStream printStream = new PrintStream(file);
        PrintStream standard = System.out;
        BufferedReader reader = new BufferedReader( new FileReader(folder+"Entrada\\Salida3.txt"));
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
        reader = new BufferedReader( new FileReader(folder + "Entrada\\Out3.txt"));
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
                fail("Se detectaron m??s palabras."+count+"/"+SET_VALUES.size());
         } catch (IOException ex) {
             Logger.getLogger(BNFEjercicio3.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
    
}
