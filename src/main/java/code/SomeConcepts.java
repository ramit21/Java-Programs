package code;

public class SomeConcepts {
    public static void main(String[] args) {
        forLoopIncrementOrder();
        testStringIntern();


        testFinally();
        testSystemExit();
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

    private static void forLoopIncrementOrder() {
        int sum = 0;
        for (int i = 0, j = 0; i < 5 && j < 5; ++i, j = i + 1) {
            sum += i;
            System.out.println("i = " + i + ", j = " + j + ", sum = " + sum);
        }
        System.out.println(sum); //Ans is 6 since increment section is executred left to right, ie first ++i, and then j=i+1
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
