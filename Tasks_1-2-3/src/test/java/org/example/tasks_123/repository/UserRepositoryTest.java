package org.example.tasks_123.repository;

import org.example.tasks_123.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired private TestEntityManager entityManager;
    @Autowired private UserRepository userRepository;

    @Test
    void findByUsernameExisting() {
        User u = new User();
        u.setUsername("testuser"); u.setEmail("t@t.com"); u.setPassword("Pass1234");
        entityManager.persist(u); entityManager.flush();

        assertTrue(userRepository.findByUsername("testuser").isPresent());
    }

    @Test
    void existsByEmailReturnsTrue() {
        User u = new User();
        u.setUsername("u1"); u.setEmail("exists@mail.com"); u.setPassword("Pass1234");
        entityManager.persist(u); entityManager.flush();

        assertTrue(userRepository.existsByEmail("exists@mail.com"));
        assertFalse(userRepository.existsByEmail("fake@mail.com"));
    }
}