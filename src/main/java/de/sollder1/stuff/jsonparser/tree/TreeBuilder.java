package de.sollder1.stuff.jsonparser.tree;

import de.sollder1.stuff.jsonparser.token.Token;
import de.sollder1.stuff.jsonparser.token.TokenType;
import de.sollder1.stuff.jsonparser.tree.exceptions.TreeBuilderException;
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

public class TreeBuilder {

    private final List<Token> inputTokens;
    private int tokenIndex;
    private JsonNode tree;

    public TreeBuilder(List<Token> inputTokens) {
        this.inputTokens = inputTokens;
    }

    public JsonNode getTree() {
        //Only actually build once
        if (tree == null) {
            if (inputTokens.size() == 0) {
                throw new TreeBuilderException("Expected at least one token!", null);
            }
            tree = buildTree();
        }

        return tree;
    }

    private JsonNode buildTree() {
        switch (currentToken().getType()) {
            case NULL:
                tokenIndex++;
                return new NullNode();
            case TRUE:
                tokenIndex++;
                return new BooleanNode("true");
            case FALSE:
                tokenIndex++;
                return new BooleanNode("false");
            case STRING_LITERAL:
                var stringValue = currentToken().getRawValue();
                tokenIndex++;
                return new StringNode(stringValue);
            case NUMBER_LITERAL:
                var numberValue = currentToken().getRawValue();
                tokenIndex++;
                return new NumericNode(numberValue);
            case BRACE_START:
                return createObjectNode();
            case SQUARE_BRACKET_START:
                return createArrayNode();
            default:
                throw new TreeBuilderException("Unexpected Token!", currentToken());
        }
    }

    private ObjectNode createObjectNode() {
        List<KeyValueNode> childNodes = new LinkedList<>();
        this.tokenIndex++;
        while (tokenIndex < inputTokens.size()) {
            childNodes.add(createKeyValueNode());
            if (currentToken().getType() == TokenType.BRACE_END) {
                this.tokenIndex++;
                break;
            } else if (currentToken().getType() == TokenType.COMMA) {
                this.tokenIndex++;
            } else {
                throw new TreeBuilderException("Expected ObjectTerminal '}' OR ValueSeparator ','", currentToken());
            }
        }
        return new ObjectNode(childNodes);
    }

    private ArrayNode createArrayNode() {
        List<JsonNode> childNodes = new LinkedList<>();
        this.tokenIndex++;
        while (tokenIndex < inputTokens.size()) {
            JsonNode arrayToken = buildTree();
            childNodes.add(arrayToken);
            if (currentToken().getType() == TokenType.SQUARE_BRACKET_END) {
                this.tokenIndex++;
                break;
            } else if (currentToken().getType() == TokenType.COMMA) {
                this.tokenIndex++;
            } else {
                throw new TreeBuilderException("Expected ArrayTerminal ']' OR ValueSeparator ','", currentToken());
            }
        }
        return new ArrayNode(childNodes);
    }

    private KeyValueNode createKeyValueNode() {
        String name;
        JsonNode value;

        if (currentToken().getType() == TokenType.STRING_LITERAL) {
            name = currentToken().getRawValue();
            this.tokenIndex++;
        } else {
            throw new TreeBuilderException("KeyValuePair has no Name", currentToken());
        }

        if (currentToken().getType() != TokenType.COLON) {
            throw new TreeBuilderException("Expected Colon", currentToken());
        }
        this.tokenIndex++;

        value = buildTree();
        return new KeyValueNode(name, value);

    }

    private Token currentToken() {
        return inputTokens.get(tokenIndex);
    }



}
