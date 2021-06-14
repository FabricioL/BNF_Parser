package bnf.ejercicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
//import javafx.util.Pair;

public class BNF {

    LinkedHashMap<Token, ArrayList<ParteDerecha>> reglas;
    ArrayList al;
    Token token_corriente;

    /**
     * Constructor para la clase BNF
     */
    public BNF() {
        this.reglas = new LinkedHashMap<Token, ArrayList<ParteDerecha>>();
    }

    /**
     * Lee desde un archivo todas las reglas de una bnf Se encarga de pasar a una
     * estructura en java el archivo Cuyo formarto sea Backus-Naur Form grammar con
     * notación extendida
     * 
     * 
     * @param reader Dado lector de archivo transforma a BNFE
     */
    public void read(BufferedReader reader) {

        try {

            String line = reader.readLine();

            while (line != null) {

                String[] partes = line.split("::=");

                String a = partes[0].replace("<", "").replace(">", "");

                Token pi = new Token(Tipo.NOTERMINAL, a);

                // partes[0];//parte derecha
                // partes[1];//parte derecha

                String[] opciones = partes[1].split("\\|");

                al = new ArrayList<ParteDerecha>();

                for (String opcion : opciones) {

                    ParteDerecha pd = new ParteDerecha();
                    int largo = opcion.length();
                    token_corriente = new Token(Tipo.TERMINAL, "");

                    for (int i = 0; i < largo; i++) {

                        switch (opcion.charAt(i)) {

                            case '<':
                                token_corriente.setTipo(Tipo.NOTERMINAL);
                                break;
                            case '>':
                                pd.addToken(token_corriente);
                                token_corriente = new Token(Tipo.TERMINAL, "");
                                break;
                            case ' ':
                                token_corriente.setTipo(Tipo.TERMINAL);
                                pd.addToken(token_corriente);
                                token_corriente = new Token(Tipo.TERMINAL, "");
                                break;
                            case '[':
                                token_corriente.setOpcion(true);
                                break;
                            case ']':
                                token_corriente.setValor("");
                                token_corriente.setOpcion(false);
                                break;
                            case '{':
                                token_corriente.setIteracion(true);
                                break;
                            case '}':
                                token_corriente.setIteracion(false);
                                break;
                            default:
                                token_corriente.addChar(opcion.charAt(i));
                        }

                    }

                    al.add(pd);

                }

                reglas.put(pi, al);/////////////////////////////////////
                line = reader.readLine();

            }

        } catch (RuntimeException e) {

            System.out.println("En tiempo de ejecucion. Mensaje:  " + e.getMessage());
            throw new RuntimeException("Problema de lectura del archivo.");

        } catch (Exception e) {

            System.out.println("Mensaje:  " + e.getMessage());
            throw new RuntimeException("Problema de lectura del archivo.");

        }
    }




    public void generarCadenas() {

        LinkedHashMap<Integer, ArrayList<ParteDerecha>> newRules = new LinkedHashMap<>();
        ListIterator<Token> it2 = new ArrayList<Token>(reglas.keySet()).listIterator(0);

        Token key1 = it2.next();
        newRules.put(0, reglas.get(key1));

        ListIterator<Integer> it = new ArrayList<Integer>(newRules.keySet()).listIterator(0);

        while (it2.hasNext()) {

            int key = it.next();
            it2.next();
            ArrayList<ParteDerecha> pd1 = new ArrayList<>(newRules.get(key));
            ArrayList<ParteDerecha> rightParts = new ArrayList<>();
            ArrayList<ParteDerecha> rightPartsArray = new ArrayList<>();

            for (ParteDerecha pd : pd1) {

                ArrayList<Token> tokens = new ArrayList<>(pd.getTokens());
                rightParts.clear();

                for (Token t : tokens) {

                    if (t.getTipo() == Tipo.NOTERMINAL) { // si el token en cuestion es un no terminal

                        ArrayList<ParteDerecha> newRightPart = new ArrayList<>(reglas.get(t));
                        // obtengo los tokens que generaria

                        if (rightParts.isEmpty()) {
                            for (ParteDerecha x : newRightPart) {
                                ParteDerecha r = new ParteDerecha();
                                for (Token n : x.getTokens())
                                    r.addToken(n);
                                rightParts.add(r);
                            }
                        } else {
                            ArrayList<ParteDerecha> oldRightPart = new ArrayList<>(rightParts);
                            rightParts.clear();
                            for (ParteDerecha orp : oldRightPart) {
                                for (ParteDerecha nrp : newRightPart) {
                                    ParteDerecha r = new ParteDerecha();
                                    for (Token t1 : orp.getTokens())
                                        r.addToken(t1);
                                    for (Token t2 : nrp.getTokens())
                                        r.addToken(t2);
                                    rightParts.add(r); // agrego a rightParts la parte derecha con los nuevos tokens
                                }
                            }
                        }

                    } else { // es un terminal

                        if (rightParts.isEmpty()) {
                            ParteDerecha r = new ParteDerecha();
                            r.addToken(t);
                            rightParts.add(r);
                        } else {
                            ArrayList<ParteDerecha> oldRightPart = new ArrayList<>(rightParts);
                            rightParts.clear();
                            for (ParteDerecha orp : oldRightPart) {
                                ParteDerecha r = new ParteDerecha();
                                for (Token t1 : orp.getTokens())
                                    r.addToken(t1);
                                r.addToken(t);
                                rightParts.add(r);
                            }
                        }
                    }

                }

                for (ParteDerecha p : rightParts) {
                    ParteDerecha r = new ParteDerecha();
                    for (Token t : p.getTokens()) {
                        r.addToken(t);
                    }
                    rightPartsArray.add(p);
                }

            }

            newRules.put(it.nextIndex(), rightPartsArray);
            ListIterator<Integer> auxIt = new ArrayList<Integer>(newRules.keySet()).listIterator(it.nextIndex());
            it = auxIt;
            // actualizar el iterador it. no encontré otra forma de seguir iterando
            // en el mapa luego de modificarlo.

        }

        String cadenas = "";
        int cantCadenas = 0;

        for (ParteDerecha pd : newRules.get(it.nextIndex())) {
            System.out.println(pd.getValores());            
        }


    }






    public void imprimirReglas() {
        // Imprimimos el Mapa que contiene todas las reglas

        Iterator it = reglas.keySet().iterator();

        System.out.println("\n");

        while (it.hasNext()) {

            Token key = (Token) it.next();

            for (ParteDerecha pd : reglas.get(key)) {
                System.out.println("Clave: " + key.getValor() + " -> Valor: " + pd.getValores());
            }

        }

        System.out.println("\n");
    }

}
