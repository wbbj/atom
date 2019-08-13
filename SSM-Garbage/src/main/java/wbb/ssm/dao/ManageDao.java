package wbb.ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import wbb.ssm.entity.Manage;

@Component
public interface ManageDao {
    String manageLogin(@Param("username") String username, @Param("password") String password);

    Manage queryManege(String username);
}
