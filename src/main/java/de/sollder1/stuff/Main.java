package de.sollder1.stuff;


import de.sollder1.stuff.jsonparser.tokenizer.Tokenizer;

public class Main {

    public static void main(String[] args) {

        Tokenizer tokenizer = new Tokenizer("null true");
        tokenizer.tokenize();
        tokenizer.print();

    }

}
