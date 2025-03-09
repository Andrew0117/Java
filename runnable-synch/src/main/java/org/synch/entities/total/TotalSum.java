package org.synch.entities.total;

public class TotalSum {

    private Long sum;

    public TotalSum() {
        this.sum = 0L;
    }

    public synchronized Long getSum() {
        return sum;
    }

    public synchronized void setSum(Long sum) {
        this.sum += sum;
    }
}
