package de.sollder1.stuff.jsonparser.tree.nodes.terminal;

public class BooleanNode extends TerminalNode {

    public BooleanNode(String rawValue) {
        super(rawValue);
    }

    public boolean getJavaValue() {
        return Boolean.parseBoolean(getRawValue());
    }

}
