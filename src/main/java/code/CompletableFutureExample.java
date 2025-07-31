package code;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future0 = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        }).thenApply(result -> result + " World"); //thenApply to transform a future result

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 10 / 0;
        }).exceptionally(ex -> 0); // Exception handling and default value return

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
                    try {
                        sleep(1000);
                        System.out.println("Future1 sleep over");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Future1 completed");
                    return "Hello";
                }, executor
        );
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
                    try {
                        sleep(1000);
                        System.out.println("Future2 sleep over");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Future2 completed");
                    return "World";
                }, executor
        );
        future1.exceptionally(e -> "Default value in case of exception"); //Exception handling and return a default value

        //chain second future in a non blocking way
        CompletableFuture<String> chainedFuture = future1.thenComposeAsync(result1 ->
                future2.thenApplyAsync(result2 -> result1 + " & " + result2)
        );

        System.out.println(chainedFuture.get());

        //block on all futures
        //CompletableFuture<Void> all = CompletableFuture.allOf(future1, future2);
        //all.join();

        //block on individual futures
        //future2.join(); //block on future 2 completion
        //System.out.println(future1.get()); //block on future 1 completion

        executor.shutdown();
        System.out.println("Main completed");
    }
}
