package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employ;
import com.example.demo.repo.EmployRepository;

@Service
public class EmployService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmployRepository employRepository;

    @Override
    public UserDetails loadUserByUsername(String employUserName) throws UsernameNotFoundException {
        Optional<Employ> employDetail = employRepository.findByEmployUserName(employUserName);
        return employDetail.map(EmployDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Employ Not Found: " + employUserName));
    }

    public String addEmploy(Employ employ) {
        employ.setPasswordHash(encoder.encode(employ.getPasswordHash()));
        employRepository.save(employ);
        return "New Employ Added Successfully";
    }

    public List<Employ> showEmploy() {
        return employRepository.findAll();
    }

    public Employ searchEmploy(int id) {
        return employRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employ not found with ID: " + id));
    }

    public String updateEmploy(Employ employ) {
        if (employRepository.existsById(employ.getEmployId())) {
            employRepository.save(employ);
            return "Employ updated successfully";
        } else {
            throw new NoSuchElementException("Employ not found with ID: " + employ.getEmployId());
        }
    }

    public String deleteEmploy(int id) {
        if (employRepository.existsById(id)) {
            employRepository.deleteById(id);
            return "Employ deleted successfully";
        } else {
            throw new NoSuchElementException("Employ not found with ID: " + id);
        }
    }
}