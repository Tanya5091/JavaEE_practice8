package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {



    @RequestMapping(value = "/booklist", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getBooks(@RequestParam (name="valu", required = false) final String value, @RequestParam (name="typ", required = false) String type) {
        List<BookDTO> res=new ArrayList<>();
        List<BookEntity> list;
        if(value==null)
        {
           list = DemoApplication.bookService.getAllBooks();
        }
        else{

            int x=Integer.parseInt(type);
            System.out.println(x);
            if(x==0)
                list = DemoApplication.bookService.findByName(value);
            else if (x==1)
                list = DemoApplication.bookService.findByIsbn(value);
            else if (x==2)
                list = DemoApplication.bookService.findByAuthor(value);
            else
                list = DemoApplication.bookService.findByNameOrIsbn(value);

        }
        System.out.println(list);
        for(BookEntity b : list)
        {
            res.add(new BookDTO(b.getBookname(), b.getIsbn(), b.getAuthor()));

            System.out.println(new BookDTO(b.getBookname(), b.getIsbn(), b.getAuthor()));
        }
        // code to get books and enrich model with those books
        return ResponseEntity.ok(res);
    }



//    @RequestMapping(value = "/exact-book", method = RequestMethod.POST)
//    public String booksList(@RequestParam int bookId) {
//        // code to get books and enrich model with those books
//        return "redirect:/book/"+bookId;
//    }


    @RequestMapping(value = "/booklist", method = RequestMethod.POST)
    public ResponseEntity<BookDTO> formControllerPost(@RequestBody final BookDTO book) {
        BookEntity ben = new BookEntity();
        ben.setBookname(book.getName());
        ben.setAuthor(book.getAuthor());
        ben.setIsbn(book.getIsbn());
       BookEntity b= DemoApplication.bookService.createBook(ben);
        System.out.println(b);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }



}
