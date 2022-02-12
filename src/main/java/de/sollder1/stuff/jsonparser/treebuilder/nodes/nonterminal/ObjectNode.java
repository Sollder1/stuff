package de.sollder1.stuff.jsonparser.treebuilder.nodes.nonterminal;

import de.sollder1.stuff.jsonparser.treebuilder.nodes.Node;

import java.util.List;

public class ObjectNode extends Node {

    public ObjectNode(List<KeyValueNode> children) {
        super(children);
    }

}
