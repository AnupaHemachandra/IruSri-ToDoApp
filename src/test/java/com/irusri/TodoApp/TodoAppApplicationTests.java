package com.irusri.TodoApp;

import com.irusri.TodoApp.model.Users;
import com.irusri.TodoApp.repo.UsersRepo;
import com.irusri.TodoApp.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest
class TodoAppApplicationTests {

	@Autowired
	private UsersService usersService;

	@MockBean
	private UsersRepo usersRepo;

	public void registerUserTest(){
		when(usersRepo.findAll()).thenReturn(value);
	}

	@Test
	void contextLoads() {
	}

}
