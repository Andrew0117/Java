package org.graber.completable.google;

import org.graber.completable.CompletableI;
import org.graber.entities.response.ResponseResultThread;
import org.graber.util.Congiguration;
import org.json.JSONObject;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class GoogleCompletable implements CompletableI {

    @Override
    public CompletableFuture<ResponseResultThread> getTotal(String searchString) {

        return CompletableFuture.supplyAsync(() -> {
            Long total = 0L;

            try {
                String urlString = Congiguration.getConfiguration().get("baseUrl.google") + "q="
                        + searchString.trim() + "&key=" + Congiguration.getConfiguration().get("api_key.google")
                        + "&cx=" + Congiguration.getConfiguration().get("search_engine_id.google");

                String contents = new String(new URL(urlString).openStream().readAllBytes(), StandardCharsets.UTF_8);

                JSONObject json = new JSONObject(contents);
                JSONObject searchInformation = json.getJSONObject("searchInformation");
                total = Long.parseLong(searchInformation.getString("totalResults"));
            } catch (Exception e) {
                // log
                System.out.println(e.getMessage());
            }

            return new ResponseResultThread(total);
        }, Congiguration.getExecutor());
    }

}
