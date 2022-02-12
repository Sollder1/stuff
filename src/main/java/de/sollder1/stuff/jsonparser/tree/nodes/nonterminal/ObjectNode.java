package de.sollder1.stuff.jsonparser.tree.nodes.nonterminal;

import de.sollder1.stuff.jsonparser.tree.nodes.JsonNode;

import java.util.List;

public class ObjectNode extends JsonNode {

    public ObjectNode(List<KeyValueNode> children) {
        super(children);
    }

}
