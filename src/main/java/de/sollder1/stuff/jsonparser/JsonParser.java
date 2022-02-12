package de.sollder1.stuff.jsonparser;

import de.sollder1.stuff.jsonparser.token.Tokenizer;
import de.sollder1.stuff.jsonparser.tree.TreeBuilder;
import de.sollder1.stuff.jsonparser.tree.TreeTokenizer;
import de.sollder1.stuff.jsonparser.tree.nodes.JsonNode;

public class JsonParser {

    public JsonNode readTree(String json) {
        return new TreeBuilder(new Tokenizer(json).getTokens()).getTree();
    }

    public String writeTree(JsonNode tree) {
        return Tokenizer.serialize(new TreeTokenizer(tree, false).getTokens());
    }

    //Warning: does ot really look that pretty...
    public String writeTreePretty(JsonNode tree) {
        return Tokenizer.serialize(new TreeTokenizer(tree, true).getTokens());
    }



}
