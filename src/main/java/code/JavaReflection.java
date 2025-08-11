package code;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Java Reflection can be used to
 * Inspect class metadata
 * Access private fields
 * Invoke methods dynamically
 */
public class JavaReflection {
    public static void main(String[] args) throws Exception {
        // Load the class dynamically
        Class<?> clazz = Class.forName("Person");

        // Create an instance
        Object personInstance = clazz.getDeclaredConstructor().newInstance();

        // Access private field 'name'
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true); // Bypass private access
        nameField.set(personInstance, "Bob");

        // Invoke method 'greet'
        Method greetMethod = clazz.getMethod("greet", String.class);
        greetMethod.invoke(personInstance, "Hello");
    }
}

class Person {
    private String name = "Alice";

    public void greet(String message) {
        System.out.println(message + ", " + name);
    }
}
