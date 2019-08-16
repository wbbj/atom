package wbb.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wbb.ssm.dao.ManageDao;
import wbb.ssm.entity.Manage;
import wbb.ssm.service.ManageService;

@Service
public class ManageServiceImpl implements ManageService {
    @Autowired
    private ManageDao manageDao;

    //登录操作
    public String manageLogin(String username, String password) {
        Manage m = manageDao.queryManege(username);
        if (m != null) {
            return password.equals(m.getPassword()) ? "1" : "-1";
        } else {
            return "-1";
        }
    }

    //用于上面登录,判断用户名和密码
    public Manage queryManage(String username) {
        Manage t = manageDao.queryManege(username);
        return t != null ? t : new Manage();
    }
}
