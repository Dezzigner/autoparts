package com.d1l.service;

import com.d1l.dao.UserDao;
import com.d1l.model.User;
import com.d1l.util.HashService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Map;

public class Authorisation extends ActionSupport implements SessionAware {

    private SessionMap<String, Object> session;

    private String login;
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

    public String logout() throws Exception {
        session.invalidate();
        return Action.SUCCESS;
    }

    public String login() throws Exception {

        User user = UserDao.getUserByLogin(this.login);
        if (user != null) {
            if (HashService.makeSHA1Hash(this.password).equals(user.getPassword())) {
                session.put("id", user.getId());
                session.put("login", user.getLogin());
                session.put("role", user.getRole().getName());
                return Action.SUCCESS;
            }
        }

        return Action.LOGIN;
    }

    public void setSession(Map<String, Object> map) {
        this.session = (SessionMap<String, Object>) map;
    }
}

