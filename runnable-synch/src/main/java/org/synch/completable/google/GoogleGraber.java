package org.synch.completable.google;

import org.json.JSONObject;
import org.synch.completable.GraberI;
import org.synch.entities.response.ResponseResultThread;
import org.synch.util.Congiguration;

import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GoogleGraber implements GraberI {

    private String searchString;

    public GoogleGraber(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public ResponseResultThread getTotal() {
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
    }

}
