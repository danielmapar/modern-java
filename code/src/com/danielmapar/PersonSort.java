package com.danielmapar;

import java.util.*;

class PersonV1 implements Comparable<PersonV1> {
    public String name;
    public PersonV1(String name) {
        this.name = name;
    }
    public int compareTo(PersonV1 person) {
        return name.compareTo(person.name);
    }
}
public class PersonSort {
    public static void main(String[] args) {
        ArrayList<PersonV1> people = new ArrayList<PersonV1>();
        people.add(new PersonV1("Same"));
        people.add(new PersonV1("Mike"));
        people.add(new PersonV1("Apple"));

        Collections.sort(people);
        for (PersonV1 person : people) {
            System.out.println(person.name);
        }
    }
}