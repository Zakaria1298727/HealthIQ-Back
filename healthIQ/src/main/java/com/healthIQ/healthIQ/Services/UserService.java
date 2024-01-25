package com.healthIQ.healthIQ.Services;
import com.healthIQ.healthIQ.Models.Doctor;
import com.healthIQ.healthIQ.Models.Role;
import com.healthIQ.healthIQ.Models.User;
import com.healthIQ.healthIQ.Repositories.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService  {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final String ADMIN_USERNAME = "healthiq23@gmail.com";
    private static final String ADMIN_PASSWORD = "12345678";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }
    @PostConstruct
    public void initAdminUser() {
        if (!emailExists(ADMIN_USERNAME)) {
            User adminUser = new User();
            adminUser.setFirstName("Default Admin");
            adminUser.setLastName("User");
            adminUser.setEmail(ADMIN_USERNAME.toLowerCase());
            adminUser.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
            adminUser.setEnabled(true);
            adminUser.setRole(Role.ROLE_ADMIN);

            userRepo.save(adminUser);
        }
    }
    public boolean emailExists(String email){
        return userRepo.findByEmail(email).isPresent();
    }

    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }
    public void enableUser(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setEnabled(true);
        userRepo.save(user);
    }

    public void changePassword(String email, String newPassword) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found change pass"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

    public List<User> findAll(){
      return  userRepo.findAll();
    }

    public int save(User user){
        userRepo.save(user);
        return 1;
    }
    public User getOne(String id ){
        return userRepo.findById(id).orElse(null);
    }



}
