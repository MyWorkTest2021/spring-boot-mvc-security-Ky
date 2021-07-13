package us.peaksoft.springbootmvcsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.peaksoft.springbootmvcsecurity.model.Role;
import us.peaksoft.springbootmvcsecurity.repository.RoleRepository;
import us.peaksoft.springbootmvcsecurity.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<String> getRoleNamesToList() {
        return roleRepository.getRoleNamesToList();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByRole(name);
    }
}
