package main.OV.service.impl;

import main.OV.db.entity.UserEntity;
import main.OV.dto.UserDto;
import main.OV.service.IUserService;
import main.OV.util.Constants;

import java.util.Collections;
import java.util.List;

public class UserService  implements IUserService, Constants {


    /**
     * @param user
     * @return
     */
    @Override
    public UserEntity saveUser(UserEntity user) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<UserDto> getUsers() {
        return Collections.emptyList();
    }

    /**
     * @return
     */
    @Override
    public List<UserDto> getUserById() {
        return Collections.emptyList();
    }

    /**
     * @param user
     * @return
     */
    @Override
    public UserEntity editUser(UserEntity user) {
        return null;
    }

    /**
     * @return Lista con los usuarios activos (que se encuentran dentro del centro)
     */
    @Override
    public List<UserDto> getActiveUsers() {
        return Collections.emptyList();
    }
}
