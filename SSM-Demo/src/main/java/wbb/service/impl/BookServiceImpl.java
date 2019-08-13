package wbb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wbb.dao.BookDao;
import wbb.entity.Book;
import wbb.service.BookService;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> getAllb() {
        System.out.println("查询");
       return bookDao.getAll();
    }
}
