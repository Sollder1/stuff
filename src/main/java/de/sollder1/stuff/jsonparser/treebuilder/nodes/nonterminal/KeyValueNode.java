package de.sollder1.stuff.jsonparser.treebuilder.nodes.nonterminal;

import de.sollder1.stuff.jsonparser.treebuilder.nodes.Node;

import java.util.Collections;

public class KeyValueNode extends Node {

    private final String identifier;

    public KeyValueNode(String identifier, Node value) {
        super(Collections.singletonList(value));
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
