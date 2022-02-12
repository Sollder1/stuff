package de.sollder1.stuff.jsonparser.tree;

import de.sollder1.stuff.jsonparser.token.Token;
import de.sollder1.stuff.jsonparser.token.TokenType;
import de.sollder1.stuff.jsonparser.tree.nodes.JsonNode;
import de.sollder1.stuff.jsonparser.tree.nodes.nonterminal.ArrayNode;
import de.sollder1.stuff.jsonparser.tree.nodes.nonterminal.KeyValueNode;
import de.sollder1.stuff.jsonparser.tree.nodes.nonterminal.ObjectNode;
import de.sollder1.stuff.jsonparser.tree.nodes.terminal.BooleanNode;
import de.sollder1.stuff.jsonparser.tree.nodes.terminal.NullNode;
import de.sollder1.stuff.jsonparser.tree.nodes.terminal.NumericNode;
import de.sollder1.stuff.jsonparser.tree.nodes.terminal.StringNode;

import java.util.LinkedList;
import java.util.List;

public class TreeTokenizer {

    private List<Token> outputTokens;
    private final JsonNode inputTree;
    private final boolean pretty;
    private int indentationLevel = 0;

    public TreeTokenizer(JsonNode inputTree, boolean pretty) {
        this.inputTree = inputTree;
        this.pretty = pretty;
    }

    //pretty = much more useless data
    public List<Token> getTokens() {
        if (outputTokens == null) {
            outputTokens = new LinkedList<>();
            buildTokenList(inputTree);
        }
        return outputTokens;
    }

    private void buildTokenList(JsonNode node) {

        switch (node) {
            case NullNode ignored -> outputTokens.add(new Token(TokenType.NULL));
            case BooleanNode n -> outputTokens.add(n.getJavaValue() ? new Token(TokenType.TRUE) : new Token(TokenType.FALSE));
            case StringNode n -> outputTokens.add(new Token(TokenType.STRING_LITERAL, n.getRawValue()));
            case NumericNode n -> outputTokens.add(new Token(TokenType.NUMBER_LITERAL, n.getRawValue()));
            case ArrayNode n -> buildArrayTokens(n);
            case ObjectNode n -> buildObjectTokens(n);
            case KeyValueNode n -> buildKeyValueTokens(n);
            default -> throw new IllegalStateException("Unexpected value: " + node);
        }

    }

    private void buildArrayTokens(ArrayNode arrayNode) {
        addTokenWithPrettySupport(new Token(TokenType.SQUARE_BRACKET_START), true, false);
        indentationLevel++;
        for (int i = 0; i < arrayNode.getChildren().size(); i++) {
            buildTokenList(arrayNode.getChildren().get(i));
            if (i < arrayNode.getChildren().size() - 1) {
                addTokenWithPrettySupport(new Token(TokenType.COMMA), true, false);
            } else {
                addTokenWithPrettySupport(null, true, true);
            }
        }
        indentationLevel--;
        addTokenWithPrettySupport(new Token(TokenType.SQUARE_BRACKET_END), false, true);
    }

    private void buildObjectTokens(ObjectNode objectNode) {
        addTokenWithPrettySupport(new Token(TokenType.BRACE_START), true, true);
        indentationLevel++;
        for (int i = 0; i < objectNode.getChildren().size(); i++) {
            buildTokenList(objectNode.getChildren().get(i));
            if (i < objectNode.getChildren().size() - 1) {
                addTokenWithPrettySupport(new Token(TokenType.COMMA), true, false);
            } else {
                addTokenWithPrettySupport(null, true, true);
            }
        }
        indentationLevel--;
        addTokenWithPrettySupport(new Token(TokenType.BRACE_END), false, true);
    }

    private void buildKeyValueTokens(KeyValueNode keyValueNode) {
        addTokenWithPrettySupport(new Token(TokenType.STRING_LITERAL, keyValueNode.getIdentifier()), false, true);
        outputTokens.add(new Token(TokenType.COLON));
        buildTokenList(keyValueNode.getChildren().get(0));
    }

    private void addTokenWithPrettySupport(Token token, boolean newLine, boolean indentation) {

        if (pretty && indentation) {
            for (int i = 0; i < indentationLevel; i++) {
                outputTokens.add(new Token(TokenType.INDENTATION));
            }
        }
        if (token != null) {
            outputTokens.add(token);
        }
        if (pretty && newLine) {
            outputTokens.add(new Token(TokenType.NEW_LINE));
        }
    }


}
