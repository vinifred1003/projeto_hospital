// package br.edu.ifpr.projeto_hospital.service;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import br.edu.ifpr.projeto_hospital.Repository.UserRepository;
// import br.edu.ifpr.projeto_hospital.Models.User;

// import java.util.ArrayList;

// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

// private final UserRepository userRepository;

// public CustomUserDetailsService(UserRepository userRepository) {
// this.userRepository = userRepository;
// }

// @Override
// public UserDetails loadUserByUsername(String username) throws
// UsernameNotFoundException {

// User user = userRepository.findByUsername(username)
// .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " +
// username));

// List<GrantedAuthority> authorities = user.getRoles().stream()
// .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
// .collect(Collectors.toList());

// return new org.springframework.security.core.userdetails.User(
// user.getUsername(),
// user.getPassword(),
// authorities
// );

// }
// }
