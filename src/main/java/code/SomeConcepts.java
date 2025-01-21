package code;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class SomeConcepts {
    private static String no = "1";

    public static void main(String[] args) {
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


        testExceptionflow();

        testFinally();
        testSystemExit();
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


}

