package com.example.oismarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Регистрация пользователя
    @PostMapping("/register")
    public LoginResponse register(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        User registeredUser = userService.registerUser(user);
        // Генерируем токен после регистрации
        String token = jwtUtil.generateToken(registeredUser.getEmail());
        return new LoginResponse(token);
    }

    // Получить всех пользователей
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new RuntimeException("Неверный email или пароль");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Неверный email или пароль");
        }
        // Генерируем токен здесь, в контроллере
        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponse(token);
    }
}