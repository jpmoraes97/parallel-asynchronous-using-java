package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;

class CompletableFutureHelloWorldTest {

    HelloWorldService hws = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorld() {
        // given

        // when
        CompletableFuture<String> completableFuture = cfhw.helloWorld();

        // then
        completableFuture
                .thenAccept(s -> {
                    assertEquals("HELLO WORLD", s);
                })
                .join();
    }

    @Test
    void helloWorld_withSize() {
        cfhw.helloWorld_withSize()
                .thenAccept(s -> {
                    assertEquals("11 - HELLO WORLD", s);
                }).join();
    }

    @Test
    void helloWorld_Multiple_Async_Calls() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_Multiple_Async_Calls();

        // then
        assertEquals("HELLO WORLD!", helloWorld);
    }

    @Test
    void helloWorld_3_Async_Calls() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_3_Async_Calls();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloWorld);
    }

    @Test
    void helloWorld_4_Async_Calls() {
        // given

        // when
        String helloWorld = cfhw.helloWorld_4_Async_Calls();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE! BYE!", helloWorld);
    }
}