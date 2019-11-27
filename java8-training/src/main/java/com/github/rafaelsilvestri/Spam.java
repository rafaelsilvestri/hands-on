package com.github.rafaelsilvestri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Spam {

    public List<String> getEmails(List<Person>... person) {
        return Stream.of(person)
                .filter(p -> p != null)
                .flatMap(List::stream)
                .filter(p -> p.getAge() > 30)
                .map(p -> p.getEmail())
                .collect(Collectors.toList());
    }
}
