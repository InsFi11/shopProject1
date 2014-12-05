package com.shop.dao;


import com.shop.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserDao {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
	public void insertData(User user) {

		sessionFactory.getCurrentSession().save(user);

	}
    @Transactional
	public List<User> getUserList() {

        List userList = sessionFactory.getCurrentSession().createQuery("from User")
                .list();

		return userList;
	}
    @Transactional
    public List<User> getUserListFromEmailandPass(String email, String password) {

        List userList = sessionFactory.getCurrentSession().createQuery("from User where email= '" + email + "' AND password='" + password + "'").list();

        return userList;
    }
    @Transactional
    public List<User> getUserListFromEmail(String email) {

        List userList = sessionFactory.getCurrentSession().createQuery("from User where email ='" + email+"'").list();
        return userList;
    }
    @Transactional
    public List<User> getUserListFromLogin(String login) {
        List userList = sessionFactory.getCurrentSession().createQuery("from User where login ='" + login+"'").list();
        return userList;
    }
    @Transactional
    public List<User> getUserListFromLoginandPass(String login, String password) {

        List userList = sessionFactory.getCurrentSession().createQuery("from User where login= '" + login + "' AND password='" + password + "'").list();
        return userList;
    }

    @Transactional
	public void deleteData(String id) {

        User user = (User) sessionFactory.getCurrentSession().get(
                User.class, Integer.valueOf(id));
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);

        }

	}

    @Transactional
	public void updateData(User user) {

        sessionFactory.getCurrentSession().update(user);
	}

    @Transactional
	public User getUser(String id) {
        User user = (User)sessionFactory.getCurrentSession().get(User.class, Integer.valueOf(id));
        return user;
	}
    @Transactional
    public int getUseridFromEmail(String email) {

        User user = (User)sessionFactory.getCurrentSession().createQuery("from User where email= " + email).list().get(0);

            return user.getUserId();

    }


}
