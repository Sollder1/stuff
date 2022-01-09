package de.sollder1.stuff.jsonparser.tokenizer;

public enum TokenType {

    //multichar literals:
    STRING_LITERAL, NUMBER_LITERAL,
    //Basic literals
    TRUE, FALSE, NULL,
    //Single char tokens:
    COLON, BRACE_START, BRACE_END, SQUARE_BRACKET_START, SQUARE_BRACKET_END

}
