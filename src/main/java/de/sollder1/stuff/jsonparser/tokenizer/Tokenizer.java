package de.sollder1.stuff.jsonparser.tokenizer;

import java.util.LinkedList;
import java.util.List;

public class Tokenizer {

    private final String inputString;
    private final List<Token> tokens;
    private int index;

    public Tokenizer(String inputString) {
        this.inputString = inputString;
        this.tokens = new LinkedList<>();
        this.index = 0;
    }


    public List<Token> tokenize() {

        while (index < inputString.length()) {
            if (isWhiteSpace()) {
                index++;
                continue;
            }
            if (findSingleCharToken()) {
                index++;
                continue;
            }
            if (findFixedLengthLiteral()) {
                continue;
            }
            if (findNumberLiteral()) {
                continue;
            }
            if (findStringLiteral()) {
                continue;
            }
            throw TokenizerException.create(inputString, index);
        }

        return tokens;
    }

    private boolean isWhiteSpace() {
        var ch = inputString.charAt(index);
        return (ch == 0x20) || (ch == 0x09) || (ch == 0xD) || (ch == 0xA);
    }

    private boolean findSingleCharToken() {
        var ch = inputString.charAt(index);
        switch (ch) {
            case ':':
                tokens.add(new Token(TokenType.COLON));
                return true;
            case ',':
                tokens.add(new Token(TokenType.COMMA));
                return true;
            case '[':
                tokens.add(new Token(TokenType.SQUARE_BRACKET_START));
                return true;
            case ']':
                tokens.add(new Token(TokenType.SQUARE_BRACKET_END));
                return true;
            case '{':
                tokens.add(new Token(TokenType.BRACE_START));
                return true;
            case '}':
                tokens.add(new Token(TokenType.BRACE_END));
                return true;
            default:
                return false;
        }
    }

    private boolean findFixedLengthLiteral() {
        if (inputString.startsWith("null", index)) {
            tokens.add(new Token(TokenType.NULL));
            index += 4;
            return true;
        } else if (inputString.startsWith("true", index)) {
            tokens.add(new Token(TokenType.TRUE));
            index += 4;
            return true;
        } else if (inputString.startsWith("false", index)) {
            tokens.add(new Token(TokenType.FALSE));
            index += 5;
            return true;
        } else {
            return false;
        }
    }

    private boolean findNumberLiteral() {

        StringBuilder literal = new StringBuilder();
        boolean negative = false;

        if (inputString.charAt(index) == '-') {
            literal.append('-');
            negative = true;
            index++;
        }

        if (!isNumeric(inputString.charAt(index))) {
            if (negative) {
                throw TokenizerException.create(inputString, index);
            } else {
                return false;
            }
        }

        boolean colonSet = false;
        boolean exponentSet = false;

        while (true) {
            var charAt = inputString.charAt(index);
            if (isNumeric(charAt)) {
                //nothing happens here...
            } else if (charAt == '.') {
                if (colonSet) {
                    throw TokenizerException.create(inputString, index);
                } else {
                    colonSet = true;
                }
            } else if (charAt == 'e' || charAt == 'E') {
                if (exponentSet) {
                    throw TokenizerException.create(inputString, index);
                } else {
                    exponentSet = true;
                }
            } else {
                break;
            }
            literal.append(charAt);
            index++;
        }

        tokens.add(new Token(TokenType.NUMBER_LITERAL, literal.toString()));

        return true;
    }

    private boolean findStringLiteral() {
        StringBuilder literal = new StringBuilder();

        if (inputString.charAt(index) != '"') {
            return false;
        } else {
            index++;
        }

        while (true) {
            var charAt = inputString.charAt(index);
            if (charAt == '"') {
                index++;
                break;
            }
            literal.append(charAt);
            index++;
        }

        tokens.add(new Token(TokenType.STRING_LITERAL, literal.toString()));

        return true;
    }

    private static boolean isNumeric(char charAt) {
        return (charAt >= '0' && charAt <= '9');
    }

    public void print() {
        this.tokens.forEach(System.out::println);
    }
}
