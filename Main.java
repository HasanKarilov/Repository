package com.company;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Unix on 3/25/17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner cin = new Scanner(System.in);
        String query;

        System.out.println("Enter what do you want to find:");
        query = cin.nextLine();

        Crawler crawler = new Crawler();
        crawler.runEngine(query);
        crawler.result();
    }
}
