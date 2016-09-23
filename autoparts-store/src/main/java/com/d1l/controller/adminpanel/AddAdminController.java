package com.d1l.controller.adminpanel;

import com.d1l.dao.RoleDao;
import com.d1l.dao.UserDao;
import com.d1l.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddAdminController extends ActionSupport {

    private User user;

    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

    public String add() {
        if (!validate(getUser().getLogin(), getUser().getPassword())) return Action.ERROR;
        if (UserDao.getUserByLogin(getUser().getLogin()) != null) {
            message = "User with this login already exist";
            return Action.SUCCESS;
        }
        int huilo = RoleDao.getRoleByName("Admin").getId();
        user.setRole(RoleDao.getRoleByName("Admin"));
        UserDao.addOrUpdateUser(getUser());
        return Action.SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private boolean validate(String login, String password) {
        Pattern loginPattern = Pattern.compile("^[A-Za-z0-9_-]{1,30}$");
        Matcher m = loginPattern.matcher(login);
        if (!m.matches())
        {
            message = "The login is invalid";
            return false;
        }
        Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9@#$%*]{8,60}$");
        m = passwordPattern.matcher(password);
        if (!m.matches())
        {
            message = "The password is invalid";
            return false;
        }
        return true;
    }
}
