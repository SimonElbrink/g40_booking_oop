package se.lexicon.io;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonManager {

    private static final JsonManager INSTANCE = new JsonManager();

    public static JsonManager getInstance(){
        return INSTANCE;
    }


    private final ObjectMapper objectMapper;

    public JsonManager() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
    }

    //Save To Json
    public <T> void serializeToJson(Collection<T> data, File file){
        try {
            objectMapper.writeValue(file,data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Read From Json
    public <T> List<T> deserializeFromJson(File file, Class<T> clazz){
        List<T> result = new ArrayList<>();

        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);

        try {
            result = objectMapper.readValue(file,type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
