package com.wetcoding.simpletodo.service.bean;

import com.wetcoding.simpletodo.repository.UserRepository;
import com.wetcoding.simpletodo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceBean implements UserService {
    private final UserRepository userRepository;


}
