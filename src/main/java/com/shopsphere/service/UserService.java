package com.shopsphere.service;
import com.shopsphere.dto.UserDto;
import com.shopsphere.entity.ShoppingCart;
import com.shopsphere.entity.User;
import com.shopsphere.repository.ShoppingCartRepository;
import com.shopsphere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartRepository shoppingCartRepository;
    @Value("${admin.secret.key}")
    private String adminSecretKey;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if ("ADMIN".equals(userDto.getRole())) {
            if (adminSecretKey.equals(userDto.getAdminSecretKey())) {
                user.setRoles(Collections.singleton("ROLE_ADMIN"));
            } else {
                throw new IllegalArgumentException("Invalid Admin Secret Key.");
            }
        } else {
            user.setRoles(Collections.singleton("ROLE_USER"));
        }
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Optional<ShoppingCart> cart = shoppingCartRepository.findByUser(user);
        cart.ifPresent(shoppingCartRepository::delete);
        userRepository.deleteById(id);
    }
}