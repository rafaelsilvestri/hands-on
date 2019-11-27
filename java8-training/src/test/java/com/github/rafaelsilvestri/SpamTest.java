package com.github.rafaelsilvestri;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpamTest {

    @Test
    public void shouldReturnListOfEmailsOfClientsAndVendorsGreaterThan30YrsOld() {
        Spam spam = new Spam();
        List<Person> clients = Arrays.asList(
                new Person(30, "Client 1", "client1@foo.bar"),
                new Person(25, "Client 2", "client2@foo.bar"),
                new Person(35, "Client 3", "client3@foo.bar"),
                new Person(31, "Client 4", "client4@foo.bar")
        );

        List<Person> vendors = Arrays.asList(
                new Person(20, "Vendor 1", "vendor1@foo.bar"),
                new Person(25, "Vendor 2", "vendor2@foo.bar"),
                new Person(38, "Vendor 3", "vendor3@foo.bar"),
                new Person(32, "Vendor 4", "vendor4@foo.bar")
        );

        List<String> expected = Arrays.asList(
                "client3@foo.bar",
                "client4@foo.bar",
                "vendor3@foo.bar",
                "vendor4@foo.bar"
        );

        List<String> result = spam.getEmails(clients, vendors);

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnListOfEmailsOfVendorsGreaterThan30YrsOld() {
        Spam spam = new Spam();
        List<Person> clients = null;

        List<Person> vendors = Arrays.asList(
                new Person(20, "Vendor 1", "vendor1@foo.bar"),
                new Person(25, "Vendor 2", "vendor2@foo.bar"),
                new Person(38, "Vendor 3", "vendor3@foo.bar"),
                new Person(32, "Vendor 4", "vendor4@foo.bar")
        );

        List<String> expected = Arrays.asList(
                "vendor3@foo.bar",
                "vendor4@foo.bar"
        );

        List<String> result = spam.getEmails(clients, vendors);

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnEmptyListIfAllIsNull() {
        Spam spam = new Spam();
        List<Person> clients = null;
        List<Person> vendors = null;

        List<String> expected = Collections.emptyList();
        List<String> result = spam.getEmails(clients, vendors);

        assertEquals(expected, result);
    }

}
