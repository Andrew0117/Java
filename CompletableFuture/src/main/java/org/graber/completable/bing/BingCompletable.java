package org.graber.completable.bing;

import org.graber.completable.CompletableI;
import org.graber.entities.response.ResponseResultThread;
import org.graber.util.Congiguration;
import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class BingCompletable implements CompletableI {

    @Override
    public CompletableFuture<ResponseResultThread> getTotal(String searchString) {

        return CompletableFuture.supplyAsync(() -> {
            Long total = 0L;

            try (WebClient webClient = new WebClient(BrowserVersion.FIREFOX)) {
                webClient.getOptions().setJavaScriptEnabled(false);
                webClient.getOptions().setCssEnabled(false);
                try {
                    HtmlPage page = webClient.getPage(Congiguration.getConfiguration().get("baseUrl.bing") + "q=" + searchString.trim());
                    HtmlElement element = (HtmlElement) page.getByXPath("//span[@class='sb_count']").get(0);
                    String text = element.asNormalizedText();
                    String[] result = text.split(" ");
                    String total_ = result[1].replace(",", "");
                    total = Long.parseLong(total_);
                } catch (IOException e) {
                    // log
                    System.out.println(e.getMessage());
                }
            }

            return new ResponseResultThread(total);
        }, Congiguration.getExecutor());
    }

}
