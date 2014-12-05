package com.shop.domain;

import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class Permission implements GrantedAuthority {

    private static final long serialVersionUID = -5404269148967698143L;
    @Id
    @Column(name="id_permission")
    private int permissionId;


    @Column(name = "permissionName", length = 50)
    private String permissionname;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
            joinColumns        = {@JoinColumn(name = "id_permission")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")}
    )
    private Set<Role> permRoles;

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    @Override
    public String getAuthority() {
        return permissionname;
    }

    public Set<Role> getPermRoles() {
        return permRoles;
    }

    public void setPermRoles(Set<Role> permRoles) {
        this.permRoles = permRoles;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d, permissionname='%s')",
                this.getClass().getSimpleName(),
                this.getPermissionId(), this.getPermissionname());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Role) {
            final Permission other = (Permission) o;
            return Objects.equal(getPermissionId(), other.getPermissionId())
                    && Objects.equal(getPermissionname(), other.getPermissionname());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPermissionId(), getPermissionname());
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
