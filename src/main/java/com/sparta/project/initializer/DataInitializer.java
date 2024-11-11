//package com.sparta.project.initializer;
//
<<<<<<< HEAD
//import com.sparta.project.repository.user.UserRepository;
=======
//import com.sparta.project.repository.UserRepository;
>>>>>>> 9863864 ([Fix] Menu Service 메서드별 테스트 완성)
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        userRepository.findAll().forEach(user -> {
//            String encodedPassword = passwordEncoder.encode(user.getPassword());
//            user.setPassword(encodedPassword);
//            userRepository.save(user);
//        });
//    }
//}
