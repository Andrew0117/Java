package org.graber;

import org.graber.completable.CompletableI;
import org.graber.completable.google.GoogleCompletable;
import org.graber.completable.bing.BingCompletable;
import org.graber.entities.response.ResponseResultThread;
import org.graber.util.Congiguration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Launcher {

    public static void main(String[] args) {
        CompletableI bingCompletable = new BingCompletable();
        CompletableI googleCompletable = new GoogleCompletable();
        CompletableFuture<ResponseResultThread> googleFuture = googleCompletable.getTotal("java");
        CompletableFuture<ResponseResultThread> bingFuture = bingCompletable.getTotal("java");

        CompletableFuture<Void> allOf = CompletableFuture.allOf(googleFuture, bingFuture);

        allOf.join();

        Long googleTotal = 0L;
        Long bingTotal = 0L;
        Long total = 0L;

        try {
            googleTotal = googleFuture.get().getTotal();
            System.out.println("GoogleTotal: " + googleTotal);
            bingTotal = bingFuture.get().getTotal();
            System.out.println("BingTotal: " + bingTotal);
            total = googleTotal + bingTotal;
            System.out.println("Total: " + total);
        } catch (InterruptedException | ExecutionException ex) {
            // log
            System.out.println(ex.getMessage());
        }

        Congiguration.executorShutdown();
    }

}
