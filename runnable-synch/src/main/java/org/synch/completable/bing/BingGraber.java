package org.synch.completable.bing;

import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;
import org.synch.completable.GraberI;
import org.synch.entities.response.ResponseResultThread;
import org.synch.util.Congiguration;

import java.io.IOException;

public class BingGraber implements GraberI {

    private String searchString;

    public BingGraber(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public ResponseResultThread getTotal() {
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
    }

}
