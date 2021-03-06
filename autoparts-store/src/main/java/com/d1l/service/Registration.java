package com.d1l.service;

import com.d1l.dao.CustomerDao;
import com.d1l.dao.RoleDao;
import com.d1l.dao.SupplierDao;
import com.d1l.dao.UserDao;
import com.d1l.model.Customer;
import com.d1l.model.Supplier;
import com.d1l.model.User;
import com.d1l.util.HashService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends ActionSupport implements SessionAware {

    private String login;
    private String password;
    private String repeatpass;
    private String firstname;
    private String middlename;
    private String lastname;
    private String companyName;
    private String message;

    public String getRepeatpass() {
        return repeatpass;
    }

    public void setRepeatpass(String repeatpass) {
        this.repeatpass = repeatpass;
    }

    public SessionMap<String, Object> getSession() {
        return session;
    }

    public void setSession(SessionMap<String, Object> session) {
        this.session = session;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private SessionMap<String, Object> session;

    public void setSession(Map<String, Object> map) {
        this.session = (SessionMap<String, Object>) map;
    }

    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

    public String singupAsCustomer() throws  Exception {

        if (!validateCustomer(getLogin(), getPassword(), getFirstname(),
                getLastname(), getMiddlename())) return Action.ERROR;

        if (UserDao.getUserByLogin(this.login) != null) {
            message = "User with this login already exist";
            return Action.SUCCESS;
        }

        User user = new User();
        Customer customer = new Customer();

        user.setLogin(this.login);
        user.setPassword(HashService.makeSHA1Hash(this.password));
        user.setRole(RoleDao.getRoleByName("Customer"));
        UserDao.addOrUpdateUser(user);

        customer.setFirstname(this.firstname);
        customer.setLastname(this.lastname);
        customer.setMiddlename(this.middlename);
        customer.setUser(UserDao.getUserByLogin(this.login));
        CustomerDao.addOrUpdateCustomer(customer);

        //auto-login after registration new account
        session.put("id", customer.getUser().getId());
        session.put("login", user.getLogin());
        session.put("role", user.getRole().getName());

        return Action.SUCCESS;
    }

    public String singupAsSupplier() throws  Exception {

        if (!validateSupplier(getLogin(), getPassword(), getCompanyName())) return Action.ERROR;

        if (UserDao.getUserByLogin(this.login) != null) {
            message = "User with this login already exist";
            return Action.SUCCESS;
        }

        User user = new User();
        Supplier supplier = new Supplier();

        user.setLogin(this.login);
        user.setPassword(HashService.makeSHA1Hash(this.password));
        user.setRole(RoleDao.getRoleByName("Supplier"));
        UserDao.addOrUpdateUser(user);

        supplier.setCompanyName(this.companyName);
        supplier.setUser(UserDao.getUserByLogin(this.login));
        SupplierDao.addOrUpdateSupplier(supplier);

        //auto-login after registration new account
        session.put("id", supplier.getUser().getId());
        session.put("login", user.getLogin());
        session.put("role", user.getRole().getName());

        return Action.SUCCESS;
    }

    private boolean validateSupplier(String login, String password, String companyName) {
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
        if (!password.equals(repeatpass)) {
            message = "Passwords not equals";
            return false;
        }
        Pattern companyNamePattern = Pattern.compile("^[A-Za-z0-9\\s]{1,60}$");
        m = companyNamePattern.matcher(companyName);
        if (!m.matches())
        {
            message = "The company name is invalid";
            return false;
        }
        return true;
    }

    private boolean validateCustomer(String login, String password, String firstname,
                                     String lastname, String middlename) {

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
        if (!password.equals(repeatpass)) {
            message = "Passwords not equals";
            return false;
        }
        Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{1,60}$");
        m = namePattern.matcher(firstname);
        if (!m.matches())
        {
            message = "The firstname is invalid";
            return false;
        }
        m = namePattern.matcher(lastname);
        if (!m.matches())
        {
            message = "The lastname is invalid";
            return false;
        }
        Pattern midnamePattern = Pattern.compile("^[A-Za-z\\s]{0,60}$");
        m = midnamePattern.matcher(getFirstname());
        if (!m.matches())
        {
            message = "The middlename is invalid";
            return false;
        }
        return true;
    }

}

