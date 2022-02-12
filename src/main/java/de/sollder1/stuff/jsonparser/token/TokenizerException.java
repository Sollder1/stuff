package de.sollder1.stuff.jsonparser.token;

public class TokenizerException extends RuntimeException {

    private static final String FORMAT = "Illegal char at postion %d (%s). Surrounding string: '%s'";
    private static final int OFFSET = 5;


    public static TokenizerException create(String inputString, int iterator) {
        int lowerBound = Math.max(iterator - OFFSET, 0);
        int upperBound = Math.min(iterator + OFFSET, inputString.length());

        return new TokenizerException(FORMAT.formatted(iterator, inputString.charAt(iterator),
                inputString.substring(lowerBound, upperBound)));
    }

    public TokenizerException(String message) {
        super(message);
    }
}
