import java.util.HashSet;
import java.util.Set;

public class HashSetExample {

    public static void main(String[] args) {
        String string1 = "Test";
        String string2 = "Test";
        System.out.println("String hash1: " + string1.hashCode());
        System.out.println("String hash2: " + string2.hashCode());
        System.out.println("String equals: " + string1.equals(string2));
        Set<String> strings = new HashSet<>();
        strings.add(string1);
        strings.add(string2);
        System.out.println("size: " + strings.size()); // 1 object in the collection
        strings.stream().forEach(System.out::println);
        ////////////////////////////////////////////////////////////
        Person person1 = new Person("name", "surname");
        Person person2 = new Person("surname", "name");
        System.out.println("Person hash1: " + person1.hashCode()); // the hashcode
        System.out.println("Person hash2: " + person2.hashCode()); // of both objects is the same
        System.out.println("Person equals: " + person1.equals(person2));
        Set<Person> persons = new HashSet<>();
        persons.add(person1);
        persons.add(person2);
        System.out.println("size: " + persons.size()); // 2 objects in the collection
        persons.stream().forEach(System.out::println);
    }

    static class Person{
        private String name;
        private String surname;

        public Person(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return name.equals(person.name) && surname.equals(person.surname);
        }

        @Override
        public int hashCode() {
            return name.hashCode() + surname.hashCode();
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    '}';
        }
    }
}
