package de.sollder1.stuff.jsonparser.tree.nodes.terminal;

import de.sollder1.stuff.jsonparser.tree.nodes.JsonNode;

public class TerminalNode extends JsonNode {

    private final String rawValue;

    protected TerminalNode(String rawValue) {
        super(null);
        this.rawValue = rawValue;
    }

    public String getRawValue() {
        return rawValue;
    }
}
