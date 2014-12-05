package com.shop.dao;


import com.shop.domain.Permission;
import com.shop.exceptions.DuplicatePermissionException;
import com.shop.exceptions.PermissionNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PermissionsDao {


    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    public void addPermission(Permission permission) throws DuplicatePermissionException {

        try {
            // if the permission is not found, then a PermissionNotFoundException is
            // thrown from the getPermission method call, and the new permission will be
            // added.
            //
            // if the permission is found, then the flow will continue from the getPermission
            // method call and the DuplicatePermissionException will be thrown.
            Permission permCheck = getPermission(permission.getPermissionname());
            String message = "The permission [" + permCheck.getPermissionname() + "] already exists";
            throw new DuplicatePermissionException(message);
        } catch (PermissionNotFoundException e) {
            getCurrentSession().save(permission);
        }
    }


    public Permission getPermission(int permission_id) throws PermissionNotFoundException {
        Permission permObject = (Permission) getCurrentSession().get(Permission.class, permission_id);
        if (permObject == null ) {
            throw new PermissionNotFoundException("Permission id [" + permission_id + "] not found");
        } else {
            return permObject;
        }
    }

    @SuppressWarnings("unchecked")

    public Permission getPermission(String usersPermission) throws PermissionNotFoundException {

        Query query = getCurrentSession().createQuery("from Permission where permissionName = :usersPermission ");
        query.setString("usersPermission", usersPermission);


        if (query.list().size() == 0 ) {
            throw new PermissionNotFoundException("Permission [" + usersPermission + "] not found");
        } else {

            List<Permission> list = (List<Permission>)query.list();
            Permission permObject = (Permission) list.get(0);

            return permObject;
        }
    }


    public void updatePermission(Permission permission) throws PermissionNotFoundException {
        Permission permissionToUpdate = getPermission(permission.getPermissionId());
        permissionToUpdate.setPermissionId(permission.getPermissionId());
        permissionToUpdate.setPermissionname(permission.getPermissionname());
        getCurrentSession().update(permissionToUpdate);
    }


    public void deletePermission(int permission_id) throws PermissionNotFoundException {
        Permission permission = getPermission(permission_id);
        if (permission != null) {
            getCurrentSession().delete(permission);
        }
    }


    @SuppressWarnings("unchecked")
    public List<Permission> getPermissions() {
        return getCurrentSession().createQuery("from Permission").list();
    }
}
