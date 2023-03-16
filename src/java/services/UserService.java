package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;

public class UserService {
    
    public User get(String email) throws Exception {
        UserDB userdb = new UserDB();
        User user = userdb.get(email);
        return user;
    }
    
    public List<User> getAll() throws Exception {
        UserDB userdb = new UserDB();
        List<User> users = userdb.getAll();
        return users;
    }
    
    public void insert(String email, String firstname, String lastname, String password, int roleID) throws Exception {
        
        User user = new User(email,firstname,lastname,password);
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleID);
        user.setRole(role);
        
        UserDB userdb = new UserDB();
        userdb.insert(user);
    }
    
    public void update(String email, String firstname, String lastname, String password, int roleID) throws Exception {
        UserDB userdb = new UserDB();
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleID);
        User user = userdb.get(email);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setRole(role);
        userdb.update(user);
    }
    
    public void delete(String email) throws Exception {
        UserDB userdb = new UserDB();
        User user = userdb.get(email);        
        userdb.delete(user);
    }




}