package com.arnold.autolibrary.services;

import com.arnold.autolibrary.model.BookDetails;
import com.arnold.autolibrary.repo.BookCopyRepo;
import com.arnold.autolibrary.repo.BookDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDetailsRepo bookDetailsRepo;
    @Autowired
    private BookCopyRepo bookCopyRepo;

    public BookDetails registerTitle(BookDetails bookDetails){
        bookDetails.setCopies(0);
        return bookDetailsRepo.save(bookDetails);
    }
    public List<BookDetails>getAllBooks(){
        return bookDetailsRepo.findAll();
    }



}
