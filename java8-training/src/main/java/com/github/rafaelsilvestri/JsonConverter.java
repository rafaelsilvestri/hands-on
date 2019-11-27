package com.github.rafaelsilvestri;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class JsonConverter {

    private ObjectMapper mapper = new ObjectMapper();

    public Optional<String> toJson(Notification notification) {
        try {
            return Optional.ofNullable(mapper.writeValueAsString(notification));
        } catch (JsonProcessingException ex) {
            System.err.println("ERROR: " + ex.getMessage());
            return Optional.empty();
        }
    }

    public <T> Optional<T> toObject(String json, Class clazz) throws JsonProcessingException {
        if (json == null) {
            return Optional.empty();
        }

        return (Optional<T>) Optional.ofNullable(mapper.readValue(json, clazz));
    }
}
