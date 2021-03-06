package com.d1l.controller.adminpanel;

import com.d1l.dao.CustomerDao;
import com.d1l.dao.RoleDao;
import com.d1l.dao.SupplierDao;
import com.d1l.dao.UserDao;
import com.d1l.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersController extends ActionSupport {

    private List<User> usersList;
    private int id;

    @Override
    public String execute() throws Exception {
        usersList = UserDao.getUsersList();
        return Action.SUCCESS;
    }

    public String delete() {
        User existUser = UserDao.getUserById(getId());
        if (existUser.getLogin().equals("admin")) return Action.SUCCESS;
        if (existUser.getRole().getName().equals("Customer")) {
            CustomerDao.deleteCustomer(CustomerDao.getCustomerByUser(existUser).getId());
        } else if (existUser.getRole().getName().equals("Supplier")) {
            SupplierDao.deleteSupplier(SupplierDao.getSupplierByUser(existUser).getId());
        }
        UserDao.deleteUser(getId());
        return Action.SUCCESS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }
}
