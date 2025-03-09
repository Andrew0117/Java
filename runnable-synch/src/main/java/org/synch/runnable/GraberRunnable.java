package org.synch.runnable;

import org.synch.completable.GraberI;
import org.synch.entities.response.ResponseResultThread;
import org.synch.entities.total.TotalSum;

public class GraberRunnable implements Runnable {

    private GraberI graber;
    private boolean done;
    private TotalSum totalSum;

    public GraberRunnable(GraberI graber, TotalSum totalSum) {
        this.graber = graber;
        this.totalSum = totalSum;
        this.done = false;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted() && !this.done) {
                ResponseResultThread responseResultThread = this.graber.getTotal();
                this.totalSum.setSum(responseResultThread.getTotal());
                this.done = true;
                Thread.currentThread().sleep(1000);
            }
        } catch (InterruptedException ex) {
            this.done = true;

            Thread.currentThread().interrupt();
        }
    }
}
