package wbb.ssm.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import wbb.ssm.entity.Garbage;

@Component
public interface GarbageDao {

    List<Garbage> getGarbageByGname(String gname);

    void insertGarbage(Garbage garbage);
}
