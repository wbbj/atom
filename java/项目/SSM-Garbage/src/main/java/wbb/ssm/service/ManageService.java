package wbb.ssm.service;

import wbb.ssm.entity.Manage;

public interface ManageService {
    /**
     * 管理员登录
     * @param username
     * @param passord
     * @return
     */
    String manageLogin(String username , String passord);

    /**
     * 通过用户名查找密码
     * @param username
     * @return
     */
    Manage queryManage(String username);
}