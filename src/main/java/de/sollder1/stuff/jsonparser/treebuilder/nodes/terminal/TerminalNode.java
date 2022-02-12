package de.sollder1.stuff.jsonparser.treebuilder.nodes.terminal;

import de.sollder1.stuff.jsonparser.treebuilder.nodes.Node;

public class TerminalNode extends Node {

    private final String rawValue;

    protected TerminalNode(String rawValue) {
        super(null);
        this.rawValue = rawValue;
    }

    public String getRawValue() {
        return rawValue;
    }
}
