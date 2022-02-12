package de.sollder1.stuff.jsonparser.object;

import de.sollder1.stuff.jsonparser.tree.nodes.JsonNode;

import java.lang.reflect.InvocationTargetException;

public class ObjectMapper<TARGET> {

    private final JsonNode tree;
    private final Class<TARGET> targetClass;
    private TARGET object;

    public ObjectMapper(JsonNode tree, Class<TARGET> targetClass) {
        this.tree = tree;
        this.targetClass = targetClass;
    }


    public TARGET getObject() throws ReflectiveOperationException {
        if (object == null) {
            //Obviously we need a no-args Constructor...
            object = targetClass.getDeclaredConstructor().newInstance();
        }
        return object;
    }
}
