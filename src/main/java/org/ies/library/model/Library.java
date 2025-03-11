package org.ies.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Data
@AllArgsConstructor

public class Library {
    private String name;
    private Map<String, Book> booksByIsbn;
    private List<Customer> customers;
    private TreeSet<BookLend> bookLends;

    public static List<Book> getBook(List<Book> books, String genre){
        List<Book> result = new ArrayList<>();
        for (var book : books){
            if (book.getGenres().contains(genre)){
                result.add(book);
            }
        }
        return result;
    }
}
