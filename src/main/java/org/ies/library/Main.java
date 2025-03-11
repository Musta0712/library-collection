package org.ies.library;


import org.ies.library.model.BookLend;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        var book = new BookLend(
                "3432",
                LocalDate.now(),
                "143214X"
        );
        System.out.println(book);
    }
}