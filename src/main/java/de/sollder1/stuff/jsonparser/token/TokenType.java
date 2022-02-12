package de.sollder1.stuff.jsonparser.token;

public enum TokenType {

    //multichar literals:
    STRING_LITERAL, NUMBER_LITERAL,
    //Basic literals
    TRUE, FALSE, NULL,
    //Single char tokens:
    COLON, COMMA, BRACE_START, BRACE_END, SQUARE_BRACKET_START, SQUARE_BRACKET_END,

    //Only Pretty Print Stuff:
    INDENTATION, NEW_LINE;

}
