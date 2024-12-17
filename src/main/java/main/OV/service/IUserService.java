package main.OV.service;

import main.OV.db.entity.UserEntity;
import main.OV.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Interface IUserService.
 */
@Service
public interface IUserService {

	UserEntity saveUser(UserEntity user);

	List<UserDto> getUsers();

	List<UserDto> getUserById();

	UserEntity editUser(UserEntity user);

    List<UserDto> getActiveUsers();
}
