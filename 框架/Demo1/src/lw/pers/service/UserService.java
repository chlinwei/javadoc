package lw.pers.service;

import lw.pers.dao.UserDao;
import lw.pers.entity.User;

public class UserService {
    private UserDao dao = new UserDao();
    public User login(User user){
        return dao.queryUser(user);
    }
    public void register(User user){
        dao.addUser(user);
    }
}




