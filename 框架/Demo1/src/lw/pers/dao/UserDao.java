package lw.pers.dao;

import lw.pers.entity.User;

public class UserDao {
    public User queryUser(User user){
        if(user.getName().equals("eric")&&user.getPassword().equals("1234")){
            //有这个用户
            return new User("eric","1234");
        }else{
            return  null;
        }
    }

    public void addUser(User user){
        System.out.println(user);
        System.out.println("注册成功");
    }
}
