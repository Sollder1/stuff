package de.sollder1.stuff.jsonparser.treebuilder.nodes;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    //Java being Java, we need that to restrict input in ObjectNode...
    private final List<? extends Node> children;

    public Node(List<? extends Node> children) {
        this.children = children;
    }

    public List<? extends Node> getChildren() {
        return children;
    }
}
