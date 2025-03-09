package org.synch;

import org.synch.completable.bing.BingGraber;
import org.synch.completable.google.GoogleGraber;
import org.synch.entities.total.TotalSum;
import org.synch.runnable.GraberRunnable;

public class Launcher {

    public static void main(String[] args) {
        TotalSum totalSum = new TotalSum();
        GraberRunnable runnableBingGraber = new GraberRunnable(new BingGraber("java"), totalSum);
        GraberRunnable runnableGoogleGraber = new GraberRunnable(new GoogleGraber("java"), totalSum);

        Thread threadBingGraber = new Thread(runnableBingGraber);
        threadBingGraber.start();

        Thread threadGoogleGraber = new Thread(runnableGoogleGraber);
        threadGoogleGraber.start();

        try {
            threadBingGraber.join();
            threadGoogleGraber.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Total: " + totalSum.getSum());
    }

}
