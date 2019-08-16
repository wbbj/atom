package wbb.dao;

import org.springframework.stereotype.Component;
import wbb.entity.Book;

import java.util.List;

@Component
public interface BookDao {
    public List<Book> getAll();
}
