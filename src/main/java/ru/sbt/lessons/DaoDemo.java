package ru.sbt.lessons;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Optional;

/**
 * Created by axelk on 22.10.2016.
 */
public class DaoDemo {
    private final UserDaoImpl userDaoImpl;

    public DaoDemo(String url) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(url); // тут чутка исправил
        userDaoImpl = new UserDaoImpl(jdbcTemplate);
    }

    public static void main(String[] args) {
        DaoDemo daoDemo =  new DaoDemo("jdbc:h2:D:/sbt_training/jdbc_archetype/database/app");
        daoDemo.createUser("root","coolPasswordToDB");
        daoDemo.findByLogin("root");
        daoDemo.listUsers();
        ///updating////
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setLogin("root2");
        updatedUser.setPasswordHash("passwordWithBlackJack");
        daoDemo.updateUser(updatedUser);
        daoDemo.listUsers();
    }

    private void updateUser(User updatedUser) {
        System.out.println("*********** Starting update user *********");
        boolean result=false;
        try {
            result = userDaoImpl.update(updatedUser);
        }catch (RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println("****** Update user is: "+result+" *********");
    }

    private void findByLogin(String login) {
        Optional<User> user = userDaoImpl.findByLogin(login);
        if (user.isPresent()){
            System.out.println(user.get());
        }else
        {
            System.out.println("User not found now");
        }
    }

    private void createUser(String login, String password){
        System.out.println("*******Creating user starting********");
        User user = new User();
        user.setLogin(login);
        String md5Hex = DigestUtils.md5Hex(password);
        user.setPasswordHash(md5Hex);
        boolean result = false;
        try {
            result = userDaoImpl.create(user);
        }catch (RuntimeException e)
        {
            System.out.println("**** User with it login already created *******");
            return;
        }
        System.out.println("******* User is created? "+result+" ********");
    }
    private void listUsers(){
        System.out.println("***** Printing users start *******");
        List<User> users = userDaoImpl.list();
        users.forEach(System.out::println);
        System.out.println("****** Printing finished *******");
    }
}
