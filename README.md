# BNF_Parser
A simple bnf parser to generate all posible strings 

The following is a JAVA program that returns all strings that can be generated from a given BNF grammar.
 The grammar is contained in a txt format file.
![Imagen2](https://user-images.githubusercontent.com/4750690/121955689-a4b22b00-cd36-11eb-9c52-ed091bb7a78f.png)
As seen in this Figure, it is to complete the generateStrings() method of the
file "BNF.java". The method should take care of printing on the screen all the strings that can be generated with the BNF.
that can be generated with the BNF.
DISCLAIMERS:
*Due to time constraints, repetitions within the BNF have been avoided.
(both for iterations and recursions) that would take a bit more programming time.
programming time. The repetition should have a limit as the strings that could be generated would be infinite.
could be generated would be infinite. For example, rules like: exp ::= digit {exp} are read by the program, but not by the program.
are read by the program, but will not be evaluated in the final delivery.
*In order to make the reading process easier, each element in the input file is separated by one or more spaces.
is separated by one or more blanks.
