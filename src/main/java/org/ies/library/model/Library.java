package org.ies.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor

public class Library {
    private String name;
    private Map<String, Book> booksByIsbn;
    private List<Customer> customers;
    private TreeSet<BookLend> bookLends;

    public List<Book> listGenreBooks(String genre) {
        List<Book> genreBooks = new ArrayList<>();
        for (Book book : booksByIsbn.values()) {
            if (book.getGenres().contains(genre)) {
                genreBooks.add(book);
            }
        }
        return genreBooks;
    }

    public List<Customer> listCustomerByZipCode(int zipCode) {
        List<Customer> result = new ArrayList<>();
        for (var customer : customers) {
            if (customer.getZipCode() == zipCode) {
                result.add(customer);
            }
        }
        return result;
    }

    public void addBookLend(String nif, String isbn) {
        bookLends.add(
                new BookLend(isbn, LocalDate.now(), nif)
        );
    }

    public boolean removeGenre(String isbn, String genre) {
        if (booksByIsbn.containsKey(isbn)) {
            Book book = booksByIsbn.get(isbn);
            return book.getGenres().remove(genre);
        } else {
            return false;
        }
    }

    public boolean hasLentBook(int customerNumber, String isbn) {

        var customer = findCustomer(customerNumber);

        if (customer != null) {
            for (var bookLend : bookLends) {
                if (bookLend.getNif().equals(customer.getNif()) &&
                        bookLend.getIsbn().equals(isbn)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Customer findCustomer(int customerNumber) {
        for (var customer : customers) {
            if (customer.getCustomerNumber() == customerNumber) {
                return customer;
            }
        }
        return null;
    }

    public Set<String> getBookGenres(String isbn) {
        if (booksByIsbn.containsKey(isbn)) {
            var book = booksByIsbn.get(isbn);
            return book.getGenres();
        }
        return null;
    }

    public List<BookLend> findBookLends(String isbn) {
        if (booksByIsbn.containsKey(isbn)) {
            List<BookLend> lends = new ArrayList<>();
            for (var bookLend : bookLends) {
                if (bookLend.getIsbn().equals(isbn)) {
                    lends.add(bookLend);
                }
            }
            return lends;
        } else {

            return null;
        }
    }
}
