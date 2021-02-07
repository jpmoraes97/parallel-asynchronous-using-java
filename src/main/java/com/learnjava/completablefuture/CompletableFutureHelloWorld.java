package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import com.learnjava.util.LoggerUtil;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {

    private final HelloWorldService hws;

    public CompletableFutureHelloWorld(HelloWorldService hws) {
        this.hws = hws;
    }

    public CompletableFuture<String> helloWorld() {
        HelloWorldService hws = new HelloWorldService();

        return CompletableFuture.supplyAsync(hws::helloWorld)
                .thenApply(String::toUpperCase);
        // .join(); // blocks a main thread until the task in completed
    }

    public CompletableFuture<String> helloWorld_withSize() {
        return CompletableFuture.supplyAsync(hws::helloWorld)
                .thenApply(String::toUpperCase)
                .thenApply(s -> s.length() + " - " + s);
    }

    public String helloWorld_approach1() {
        String hello = hws.hello();
        String world = hws.world();
        return hello + world;
    }

    public String helloWorld_Multiple_Async_Calls() {

        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());

        String hw = hello.thenCombine(world, (h, w) -> h + w) // first, second
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return hw;
    }

    public String helloWorld_3_Async_Calls() {

        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String hw = hello
                .thenCombine(world, (h, w) -> h + w) // first, second
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return hw;
    }

    public static void main(String[] args) {
        HelloWorldService hws = new HelloWorldService();

        CompletableFuture.supplyAsync(hws::helloWorld)
                .thenApply(String::toUpperCase)
                .thenAccept(LoggerUtil::log)
                .join(); // blocks a main thread until the task in completed

        log("DONE!");

        //delay(2000);
    }
}
