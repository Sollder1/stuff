package de.sollder1.stuff.jsonparser.tree.nodes;

import java.util.List;

public abstract class JsonNode {

    //Java being Java, we need that to restrict input in ObjectNode...
    private final List<? extends JsonNode> children;

    public JsonNode(List<? extends JsonNode> children) {
        this.children = children;
    }

    public List<? extends JsonNode> getChildren() {
        return children;
    }
}
