package org.synch.entities.response;

public class ResponseResultThread {

    private Long total;

    public ResponseResultThread() {
    }

    public ResponseResultThread(Long total) {
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ResponseResultThread{" +
                "total=" + total +
                '}';
    }
}
