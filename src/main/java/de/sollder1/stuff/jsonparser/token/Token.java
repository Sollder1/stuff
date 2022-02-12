package de.sollder1.stuff.jsonparser.token;

public class Token {

    //Welche Token gibt es?

    private final TokenType type;
    private String rawValue;

    public Token(TokenType type, String rawValue) {
        this.type = type;
        this.rawValue = rawValue;
    }

    public Token(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }

    public String getRawValue() {
        return rawValue;
    }

    @Override
    public String toString() {
        if(rawValue == null) {
            return type.toString();
        }else {
            return type + ": '" + rawValue + "'";
        }
    }
}
