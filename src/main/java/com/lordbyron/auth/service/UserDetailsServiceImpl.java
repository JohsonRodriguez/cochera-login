package com.lordbyron.auth.service;

import com.lordbyron.auth.model.MyUserPrincipal;
import com.lordbyron.auth.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var employee = repository.findByUsername(username);
        if (employee == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(employee);
    }
}
