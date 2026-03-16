package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Role;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initDefaultAccounts() {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ADMIN")));
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));

        if (accountRepository.findByLoginName("admin").isEmpty()) {
            Account admin = new Account();
            admin.setLoginName("admin");
            admin.setPassword(passwordEncoder.encode("123"));
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            roles.add(userRole);
            admin.setRoles(roles);
            accountRepository.save(admin);
        }

        if (accountRepository.findByLoginName("user1").isEmpty()) {
            Account user = new Account();
            user.setLoginName("user1");
            user.setPassword(passwordEncoder.encode("123"));
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
            accountRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByLoginName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user " + username));

        return new org.springframework.security.core.userdetails.User(
                account.getLoginName(),
                account.getPassword(),
                account.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toSet())
        );
    }
}
