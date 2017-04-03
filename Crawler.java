package com.company;

import com.company.engine.Bing;
import com.company.engine.Google;
import com.company.engine.SearchEngine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unix on 3/25/17.
 */
public class Crawler extends SearchEngine {
    private Document google;
    private Document bing;

    private static final String OUTPUT_PATTERN = "ENGINE - %s: TITLE - '%s'; URL - %s";


    private List<Google> googleData;
    private List<Bing> bingData;

    public Crawler() {
        System.out.println("Please wait until program gets result!" + "\nLoading...");
        googleData = new ArrayList<Google>();
        bingData = new ArrayList<Bing>();
    }

    public void runEngine(String query) throws IOException {
        google = getDocument(GOOGLE, query);
        bing = getDocument(BING, query);

    }

    public void setSearchResults() {
        Elements googleElements = google.select("div.g").select("h3.r");
        Elements bingElements = bing.select("li.b_algo").select("h2");

        Google google = new Google();
        google.setTitle(googleElements.get(0).select("a").text());
        google.setUrl(googleElements.get(0).select("a").attr("href"));
        googleData.add(google);

        Bing bing = new Bing();
        bing.setTitle(bingElements.get(0).select("a").text());
        bing.setUrl(bingElements.get(0).select("a").attr("href"));
        bingData.add(bing);

    }

    public void result() {
        setSearchResults();
        System.out.format("\n%s \t %s \t %s", "Search Engine ", "Title", "URL\n");
        try
        {
            System.out.println(String.format(OUTPUT_PATTERN, "GOOGLE: ", googleData.get(0).getTitle(), googleData.get(0).getUrl()));
            System.out.println(String.format(OUTPUT_PATTERN, "BING: ", bingData.get(0).getTitle(), bingData.get(0).getUrl()));
        }
        catch (IllegalArgumentException e) {
        }
    }

    public Document getDocument(String searchEngine, String query) throws IOException {
        // these user agent for Unix OS
        return Jsoup.connect(searchEngine + URLEncoder.encode(query)).userAgent(USER_AGENT_LINUX).get();
    }
}