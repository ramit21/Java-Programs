package code;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class SomeConcepts {
    private static String no = "1";

    public static void main(String[] args) {

        String s1 = "Hello";
        testStringPassing(s1);
        System.out.println("String was passed by value, its still " + s1); //Ans. Hello

        alphabetIndex();

        forLoopIncrementOrder();
        testStringIntern();
        testAssertFeature();
        comparatorSort();
        System.out.println("Output from catch/finally block = " + testFinallyReturn()); //ans 3.

        //compile errors below
        //Set<? super IOException> testGenericSet = new HashSet<? super IOException>();
        //Set<? super IOException> testGenericSet3 = new HashSet<FileNotFoundException>();
        //Set<? super IOException> testGenericSet3 = new HashSet<RuntimeException>();
        //below are allowed
        Set<? super IOException> testGenericSet = new HashSet<IOException>();
        Set<? super IOException> testGenericSet2 = new HashSet<Exception>();

        testSetKey();

        testExceptionflow();
        testFinally();
        //testError(); //uncomment to test this one
        testSystemExit();
    }

    private static void alphabetIndex() {
        String str = "bat";
        for (Character c : str.toCharArray()) {
            int pos = (c - 'a');
            System.out.print(c + " is alphabet no " + pos + " ; ");
        }
        System.out.println();
    }


    private static void testError() {
        try {
            throw new Error();
        } catch (Exception e) { //catch Error instead, but not recommended
            System.out.println("Caught exception");
        } finally {
            System.out.println("Finally is printed before Error occurs");
        }
    }

    private static void testStringPassing(String s1) {
        s1 = "World";
    }

    private static int testFinallyReturn() {
        try {
            int i = 1 / 0;
            return 1;
        } catch (ArithmeticException ex) {
            return 2;
        } finally {
            return 3;
        }
    }

    private static void testSetKey() {
        //If hashcode and equals are not overidden, then HashSet uses identity '==' to decide if 2 objects are equal,
        //else it uses the hashcode.
        //Hence never manipulate a member variable that is driving teh hashcode after it has been inserted into hashcode.
        Set<Key> set = new HashSet<>();
        Key k1 = new Key(1);
        Key k2 = new Key(2);
        set.add(k1);
        set.add(k1);
        set.add(k2);
        set.add(k2);
        System.out.println("Set sizes:"); //Ans: 2 2 1 1
        System.out.print(set.size() + " ");
        k2.i = 1; //even though hashcode of both objects are now same, k2 will stay in the set with its original has value of 2
        System.out.print(set.size() + " ");
        set.remove(k1);
        System.out.print(set.size() + " ");
        set.remove(k2);//k2 can't be removed as it was inserted with hashcode of 2, but now removal is being attempted with hashcode of 1
        System.out.print(set.size() + " ");
        System.out.println();
    }

    private static void testExceptionflow() {
        firstMethod();
        System.out.println("Exception flow answer = " + no); //Ans. 142
    }

    private static void firstMethod() {
        try {
            secondMethod();
        } catch (Exception ex) {
            no += 2;
        }
    }

    private static void secondMethod() throws Exception {
        try {
            thirdMethod();
            no += 3;
        } catch (Exception ex) {
            throw new Exception();
        } finally {
            no += 4;
        }
        no += 5;
    }

    private static void thirdMethod() throws Exception {
        throw new Exception();
    }

    private static void forLoopIncrementOrder() {
        int sum = 0;
        for (int i = 0, j = 0; i < 5 && j < 5; ++i, j = i + 1) {
            sum += i;
            System.out.println("i = " + i + ", j = " + j + ", sum = " + sum);
        }
        System.out.println(sum); //Ans is 6 since increment section is executed left to right, ie first ++i, and then j=i+1
    }

    private static void testStringIntern() {
        String s1 = new String("Hello ");
        s1 += "world";
        String s2 = "Hello";
        s2 += " ";
        s2 += "world";
        System.out.println(s1.equals(s2)); //true
        System.out.println(s1 == s2); //false
        String s3 = s1.intern();
        String s4 = s2.intern();
        System.out.println(s3.equals(s4)); //true
        System.out.println(s3 == s4); //true
    }

    private static void testAssertFeature() {
        Integer no = 10;
        no++;
        assert no == null && no >= 0;
        System.out.println("no incremented fine if assertions not enabled (vm arg -ea) when running this code = " + no);
    }

    private static void comparatorSort() {
        Integer[] arr = {4, 5, 3, 1, 2};
        Comparator<Integer> comp = (num1, num2) -> num2.compareTo(num1);
        Arrays.sort(arr, comp);
        for (Integer i : arr) {
            System.out.print(i + " "); //Ans. 5 4 3 2 1
        }
        System.out.println();
    }

    private static void testFinally() {
        try {
            return;
        } finally {
            System.out.println("Finally block executed despite return in try block.");
        }
    }

    private static void testSystemExit() {
        try {
            System.out.println("Try block exits without finally block executed.");
            System.exit(0);
        } finally {
            System.out.println("not called");
        }
    }

    private static class Key {
        public int i;

        public Key(int i) {
            this.i = i;
        }

        @Override
        public boolean equals(Object o) {
            return i == ((Key) o).i;
        }

        @Override
        public int hashCode() {
            return i;
        }
    }

}

