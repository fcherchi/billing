package org.fcherchi.lm.application.config;

import org.fcherchi.lm.data.exceptions.BadConfigurationException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

/**
 * Parses a JSON string and gets an map of objects
 */
public class FileConfigurationParser {

    /** Engine name used in the paresr */
    public static final String JAVASCRIPT_ENGINE_NAME = "javascript";
    /** Composition of the object providing a json */
    private final JsonProvider jsonProvider;

    /**
     * Contructs a parser
     * @param jsonProvider The object which retrieves the json out of somewhere
     */
    public FileConfigurationParser(JsonProvider jsonProvider) {
        this.jsonProvider = jsonProvider;
    }


    /**
     * Parses a json
     * @return the json as a Map
     */
    public Map<String, Object> parseJson() {

        ScriptEngine engine = new ScriptEngineManager().getEngineByName(JAVASCRIPT_ENGINE_NAME);

        Object result;
        try {
            result = engine.eval(this.jsonProvider.getJson());
        } catch (ScriptException e) {
            throw new BadConfigurationException("Cannot read configuration file.", e);
        }
        return (Map<String, Object>) result;
    }
}
