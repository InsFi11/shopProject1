package com.shop.servises;

import com.shop.dao.SecurityUser;
import com.shop.dao.UserDao;
import com.shop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        // load user
        List<User> userList = userDao.getUserListFromLogin(username);
        User user;
        if(!userList.isEmpty())
            user = userDao.getUserListFromLogin(username).get(0);
        else user = null;

        if (user != null) {

            // convert roles

            // List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

            //roles.add(new GrantedAuthorityImpl("ROLE_USER"));
            Collection<GrantedAuthority> authorities = user.getAuthorities();


            // initialize user
            SecurityUser securityUser = new SecurityUser(
                    user.getLogin(),
                    user.getPassword(),
                    true, true, true, true,
                    authorities
            );

            securityUser.setUser(user);

            return securityUser;
        } else {
            throw new UsernameNotFoundException("No user with username '" + username + "' found!");
        }
    }
    public void insertData(User user) {
        userDao.insertData(user);
    }


    public List<User> getUserList() {
        return userDao.getUserList();
    }


    public void deleteData(String id) {
        userDao.deleteData(id);

    }
    public List<User> getUserListFromLoginandPass(String login, String password){return userDao.getUserListFromLoginandPass(login,password);}
    public List<User> getUserListFromEmailandPass(String email, String password){return userDao.getUserListFromEmailandPass(email,password);}
    public List<User> getUserListFromLogin(String login) { return userDao.getUserListFromLogin(login);}
    public List<User> getUserListFromEmail(String email) { return userDao.getUserListFromEmail(email);}
    public User getUser(String id) {
        return userDao.getUser(id);
    }
    public int getUseridFromEmail(String email) {return  userDao.getUseridFromEmail(email);}

    public void updateData(User user) {
        userDao.updateData(user);

    }



}
