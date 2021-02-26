package com.codegym.wdbsspringboot.config;

import com.codegym.wdbsspringboot.model.AppUser;
import com.codegym.wdbsspringboot.repository.IAppUserRepository;
import com.codegym.wdbsspringboot.service.userservice.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class WebSecurity {
    @Autowired
    private IAppUserRepository userRepository;

    public boolean checkUserId(Authentication authentication, String id) {
        String name = authentication.getName();
        AppUser result = userRepository.findByUsername(name);
        return result != null && result.getId().toString().equals(id);
    }
}
