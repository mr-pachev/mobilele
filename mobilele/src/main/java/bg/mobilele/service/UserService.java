package bg.mobilele.service;

import bg.mobilele.model.DTO.UserDTO;
import bg.mobilele.model.DTO.UserRegistrationDTO;

import java.util.List;

public interface UserService {
    //add  new user
    void registrationUser(UserRegistrationDTO userRegistrationDTO);
    //check is exist user by username
    boolean isExistUser(String username);
    //check is exist email
    boolean isExistEmail(String email);

    //get all users
    List<UserDTO> getAllUsers();
}
