package nz.co.sparkdigital.core.components;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to perform operations on the JSON objects.
 *
 */
public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil() {
        throw new Error("Do not try to instantiate me!");
    }

    private static Gson gsonConverter = new GsonBuilder().create();




    /**
     * Converts object type into a JSON string.
     *
     * @param sourceObject - object to be converted
     * @return a JSON string representing the given sourceObject
     */
    public static <T> String convertToString(final T sourceObject) {
        return gsonConverter.toJson(sourceObject);
    }

    /**
     * Converts a JSON string to a target object.
     *
     * @param jsonString - JSON string to be converted
     * @param clazz - the type of the target object
     * @return the target object of the class specified by clazz
     */
    public static <T> T convertToObject(final String jsonString, final Class<T> clazz) {
        return gsonConverter.fromJson(jsonString, clazz);
    }

    /**
     * Converts a JSON string into a JsonObject.
     *
     * @param jsonString - the JSON String to be converted
     * @return a JsonObject parsed from specified string; empty JsonObject if string is empty or
     *         null
     */
    public static JsonObject convertStringToJson(final String jsonString) {
        if (StringUtils.isEmpty(jsonString)) {
            return new JsonObject();
        }
        return new JsonParser().parse(jsonString).getAsJsonObject();
    }
}
