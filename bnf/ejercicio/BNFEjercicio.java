/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bnf.ejercicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author LenovoF
 */
public class BNFEjercicio {
    static BNF bnf_desde_archivo = new BNF();
    static JFileChooser fileChooser = new JFileChooser();
    
//    public static void main(String[] args) {
//        bnf_desde_archivo = new BNF();
//        FileReader fileContents = null;
//        BufferedReader fileContentsB = null;
//        int fileReturn = fileChooser.showOpenDialog(fileChooser);
//        if (fileReturn == JFileChooser.APPROVE_OPTION) {
//            File openFile = fileChooser.getSelectedFile();
//            try {
//                fileContents = new FileReader(openFile);
//                fileContentsB = new BufferedReader(fileContents);
//                bnf_desde_archivo.read(fileContentsB);
//                
//                bnf_desde_archivo.generarCadenas();
//                fileContents.close();
//                
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
   
    public static void main(String[] args) {
        bnf_desde_archivo = new BNF();
        BufferedReader fileContentsB = null;
        try {
            fileContentsB = new BufferedReader(new FileReader(args[0]));
            bnf_desde_archivo.read(fileContentsB);
            bnf_desde_archivo.generarCadenas();
            //bnf_desde_archivo.imprimirReglas();
            fileContentsB.close();

        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}
