package de.sollder1.stuff.jsonparser.treebuilder.exceptions;

import de.sollder1.stuff.jsonparser.tokenizer.Token;

public class TreeBuilderException extends RuntimeException {

    private transient Token failedToken;

    public TreeBuilderException(String message, Token failedToken) {
        super(message);
        this.failedToken = failedToken;
    }
}
