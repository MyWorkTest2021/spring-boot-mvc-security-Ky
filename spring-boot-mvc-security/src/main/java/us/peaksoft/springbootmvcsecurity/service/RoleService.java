package us.peaksoft.springbootmvcsecurity.service;

import us.peaksoft.springbootmvcsecurity.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> allRoles();
    List<String> getRoleNamesToList();
    Role getRoleByName(String name);
}
