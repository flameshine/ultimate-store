package com.example.app;

import javax.persistence.EntityNotFoundException;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.example.app.service.UserService;
import com.example.app.util.TestData;
import com.example.app.entity.User;

import static org.testng.Assert.*;

@SpringBootTest
@Transactional
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService service;

    @Test(dataProviderClass = TestData.class, dataProvider = "users", priority = 1)
    public void testFindAll(User user) {

        service.save(user);

        var databaseUsers = service.findAll();

        assertNotNull(databaseUsers);

        assertFalse(databaseUsers.isEmpty());

        assertTrue(databaseUsers.contains(user));
    }

    @Test(dataProviderClass = TestData.class, dataProvider = "users", priority = 2)
    public void testFindById(User user) {

        service.save(user);

        var databaseUser = service.findById(user.getId());

        assertNotNull(databaseUser);

//      we need to compare this objects as a strings due to some hibernate data fetching features
        assertEquals(databaseUser.toString(), user.toString());
    }

    // TODO: find a good way to test service layer save() method.

    @Test(dataProviderClass = TestData.class, dataProvider = "users", expectedExceptions = EntityNotFoundException.class, priority = 3)
    public void testDelete(User user) {

        service.save(user);

        service.delete(user);

        service.findById(user.getId());

        fail();
    }
}