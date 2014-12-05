package com.shop.domain;

import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements Serializable, GrantedAuthority  {

    private static final long serialVersionUID = 6874667425302308430L;
    @Id
    @Column(name="id_role")
    private int roleId;

    @Column(name = "roleName", length = 50)
    private String rolename;

    //@OneToMany(cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns        = {@JoinColumn(name = "id_role")},
            inverseJoinColumns = {@JoinColumn(name = "id_user")}
    )
    private Set<User> userRoles;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
            joinColumns        = { @JoinColumn(name = "id_role") },
            inverseJoinColumns = { @JoinColumn(name = "id_permission") }
    )
    private Set<Permission> permissions;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<User> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<User> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d, roleName='%s')",
                this.getClass().getSimpleName(),
                this.getRoleId(), this.getRolename());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Role) {
            final Role other = (Role) o;
            return Objects.equal(getRoleId(), other.getRoleId())
                    && Objects.equal(getRolename(), other.getRolename());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getRoleId(), getRolename());
    }

    @Override
    public String getAuthority() {
        return getRolename();
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}