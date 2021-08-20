package org.fcherchi.lm.application;

import org.fcherchi.lm.application.config.FileConfigurationProvider;
import org.fcherchi.lm.application.config.FileConfigurationParser;
import org.fcherchi.lm.application.config.FileJsonProvider;
import org.fcherchi.lm.business.taxes.ProductCategoryValidator;
import org.fcherchi.lm.data.exceptions.BadConfigurationException;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Application {

    public static final String TAX_CONFIG_FILE_NAME = "taxconfig.json";

    public static void main(String[] args) {

        Path path = getPathFromConfigFile();

        FileConfigurationParser fc = new FileConfigurationParser(new FileJsonProvider(path));
        Map<String, Object> deserializedConfig = fc.parseJson();
        //fake validator, all accepted
        ProductCategoryValidator productCategoryValidator = productCategoryId -> true;
        // ProductCategoryValidator productCategoryValidator = new HashMapDataStorage();
        FileConfigurationProvider configurationProvider = new FileConfigurationProvider(deserializedConfig, productCategoryValidator);
        configurationProvider.initializeConfiguration();
    }

    private static Path getPathFromConfigFile() {
        URL resource = Application.class.getClassLoader().getResource(TAX_CONFIG_FILE_NAME);
        Path path = null;
        if (resource == null) {
            throw new BadConfigurationException(String.format("Configuration file could not be found! '%s'", TAX_CONFIG_FILE_NAME));
        } else {
            try {
                path = Paths.get(resource.toURI());
            } catch (URISyntaxException e) {
                throw new BadConfigurationException(String.format("Cannot get access to path '%s'", path.getFileName()), e);
            }
        }
        return path;
    }


}