package de.sollder1.stuff.jsonparser.tree.nodes.nonterminal;

import de.sollder1.stuff.jsonparser.tree.nodes.JsonNode;

import java.util.Collections;

public class KeyValueNode extends JsonNode {

    private final String identifier;

    public KeyValueNode(String identifier, JsonNode value) {
        super(Collections.singletonList(value));
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
