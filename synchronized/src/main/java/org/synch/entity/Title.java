package org.synch.entity;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.BrowserVersionFeatures;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Title {

    private Set<String> titles;

    public Title() {
        titles = Collections.synchronizedSet(new HashSet<>());
    }

    public synchronized void setTitles(String url)
            throws IOException, FailingHttpStatusCodeException, MalformedURLException {
        WebClient webClient = new WebClient(getBrowser());
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);
        String title = htmlPage.getTitleText();
        titles.add(title);
    }

    private static final BrowserVersion getBrowser() {
        String applicationName = "Netscape";
        String applicationVersion = "5.0 (Windows)";
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0";
        int browserVersionNumeric = 24;
        return new BrowserVersion(applicationName, applicationVersion, userAgent, browserVersionNumeric) {
            @Override
            public boolean hasFeature(BrowserVersionFeatures property) {
                return BrowserVersion.FIREFOX_24.hasFeature(property);
            }
        };
    }

    public synchronized Set<String> getTitles() {
        return titles;
    }
}
