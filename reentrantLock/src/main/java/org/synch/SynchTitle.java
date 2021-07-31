package org.synch;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import org.synch.entity.Title;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class SynchTitle {

    public static void main(String[] args) {
        AtomicBoolean atomicBooleanFacebook = new AtomicBoolean(false);
        AtomicBoolean atomicBooleanYahoo = new AtomicBoolean(false);
        AtomicBoolean atomicBooleanPinterest = new AtomicBoolean(false);
        var title = new Title();

        Runnable runnableFacebook = () -> {
            try {
                while (!Thread.currentThread().isInterrupted() && !atomicBooleanFacebook.get()) {
                    title.setTitles("https://www.facebook.com/");
                    atomicBooleanFacebook.set(true);
                }
            } catch (IOException | FailingHttpStatusCodeException e) {
                Thread.currentThread().interrupt();
            }
        };
        Runnable runnableYahoo = () -> {
            try {
                while (!Thread.currentThread().isInterrupted() && !atomicBooleanYahoo.get()) {
                    title.setTitles("https://www.yahoo.com/");
                    atomicBooleanYahoo.set(true);
                }
            } catch (IOException | FailingHttpStatusCodeException e) {
                Thread.currentThread().interrupt();
            }
        };
        Runnable runnablePinterest = () -> {
            try {
                while (!Thread.currentThread().isInterrupted() && !atomicBooleanPinterest.get()) {
                    title.setTitles("https://www.pinterest.com/");
                    atomicBooleanPinterest.set(true);
                }
            } catch (IOException | FailingHttpStatusCodeException e) {
                Thread.currentThread().interrupt();
            }
        };
        var threadFacebook = new Thread(runnableFacebook);
        var threadYahoo = new Thread(runnableYahoo);
        var threadPinterest = new Thread(runnablePinterest);
        threadFacebook.start();
        threadYahoo.start();
        threadPinterest.start();

        while (title.getTitles().size() != 3) {
            // waiting in the main thread
        }

        title.getTitles().stream().forEach(System.out::println);
    }

}
