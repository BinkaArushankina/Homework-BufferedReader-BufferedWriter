package Homework;

public class Person implements Comparable<Person>{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public int compareTo(Person person){
        return this.age-person.age;
    }

    public String toString() {
        return "name = " + name +
                ", age = " + age;
    }
}
