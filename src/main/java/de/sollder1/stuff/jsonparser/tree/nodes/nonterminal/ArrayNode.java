package de.sollder1.stuff.jsonparser.tree.nodes.nonterminal;

import de.sollder1.stuff.jsonparser.tree.nodes.JsonNode;

import java.util.List;

public class ArrayNode extends JsonNode {

    public ArrayNode(List<JsonNode> children) {
        super(children);
    }

}
