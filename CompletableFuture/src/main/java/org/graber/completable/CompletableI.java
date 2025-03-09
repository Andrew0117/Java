package org.graber.completable;

import org.graber.entities.response.ResponseResultThread;

import java.util.concurrent.CompletableFuture;

public interface CompletableI {

    CompletableFuture<ResponseResultThread> getTotal(String searchString);

}
