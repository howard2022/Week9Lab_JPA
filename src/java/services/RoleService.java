package services;

import dataaccess.RoleDB;
import java.util.*;
import models.Role;

public class RoleService {

    public Role get(int roleId) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
        return role;
    }



}