package wbb.ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import wbb.ssm.entity.Manage;

@Component
public interface ManageDao {

    Manage queryManege(String username);
}
