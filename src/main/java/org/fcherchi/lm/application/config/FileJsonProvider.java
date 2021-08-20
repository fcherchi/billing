package org.fcherchi.lm.application.config;

import org.fcherchi.lm.data.exceptions.BadConfigurationException;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileJsonProvider implements JsonProvider {

    private final Path path;

    public FileJsonProvider(Path path) {
        this.path = path;
    }

    @Override
    public String getJson() {
        String script;
        try {
            String json = new String(Files.readAllBytes(path));
            script = String.format("Java.asJSONCompatible(%s)", json);
        } catch (Exception e) {
            throw new BadConfigurationException("Cannot read configuration file.", e);
        }
        return script;
    }
}
