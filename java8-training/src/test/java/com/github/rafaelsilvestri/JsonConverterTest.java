package com.github.rafaelsilvestri;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class JsonConverterTest {

    @Test
    public void shouldReturnJsonString() throws JsonProcessingException {
        JsonConverter converter = new JsonConverter();
        Notification notification = new Notification("foo@bar.com", "MyNotification", "This is my notification");

        String expected = "{\"email\":\"foo@bar.com\",\"name\":\"MyNotification\",\"text\":\"This is my notification\"}";

        Optional<String> result = converter.toJson(notification);

        assertEquals(expected, result.get());
    }

    @Test
    public void shouldReturnEmptyStringIfObjectIsNull() throws JsonProcessingException {
        JsonConverter converter = new JsonConverter();
        Notification notification = null;

        Optional<String> result = converter.toJson(notification);

        //assertTrue(result.isPresent());
        assertEquals("null", result.get());
    }


    @Test
    public void shouldReturnNotificationInstance() throws JsonProcessingException {
        JsonConverter converter = new JsonConverter();

        String json = "{\"email\":\"foo@bar.com\",\"name\":\"MyNotification\",\"text\":\"This is my notification\"}";

        Notification expected = new Notification("foo@bar.com", "MyNotification", "This is my notification");

        Optional<Object> result = converter.toObject(json, Notification.class);

        assertEquals(expected, result.get());
    }


    @Test
    public void shouldReturnNullIfJsonIsNull() throws JsonProcessingException {
        JsonConverter converter = new JsonConverter();

        String json = null;
        Notification expected = null;

        Optional<Notification> result = converter.toObject(json, Notification.class);

        assertFalse(result.isPresent());
    }


}
