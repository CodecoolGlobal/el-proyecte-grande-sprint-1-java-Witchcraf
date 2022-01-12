package com.codecool.PawPrint.service;

import com.codecool.PawPrint.model.entity.Search;
import com.codecool.PawPrint.model.entity.User;
import com.codecool.PawPrint.model.entity.UserType;
import com.codecool.PawPrint.model.service.ServiceOffered;
import com.codecool.PawPrint.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUser() {
        return userDao.getAll();
    }

    public void registerUser(User user) {
        userDao.add(user);
    }

    public User findUserById(int id) {
       return userDao.findById(id);
    }

    public User findUserByName(String name) {
       return userDao.findByName(name);
    }

    public void saveSearch(int userId, Set<ServiceOffered> services) {
        User user = findUserById(userId);
        Search search = new Search(services);
        userDao.add(user, search);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByName(username);
        Collection<SimpleGrantedAuthority> userTypes = null;
        if(user == null){
            throw new UsernameNotFoundException("Not found this User!");
        }
        assert false;
        userTypes.add(new SimpleGrantedAuthority(user.getType().toString()));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), userTypes);
    }

    /*public List<UserPersonalDetailsDTO> getAllUserPersonalDetails() {
        return ((List<User>) userDao
                .getAll())
                .stream()
                .map(this::convertToUserLocationDTO)
                .collect(Collectors.toList());
    }

    private UserPersonalDetailsDTO convertToUserLocationDTO(User user) {
        UserPersonalDetailsDTO userLocationDTO = new UserPersonalDetailsDTO();
        userLocationDTO.setId(user.getId());
        userLocationDTO.setUserName(user.getUserName());
        userLocationDTO.setEmail(user.getEmail());
        userLocationDTO.setPassword(user.getPassword());
        userLocationDTO.setType(user.getType());
        userLocationDTO.setRegistrationTime(user.getRegistrationTime());
        return userLocationDTO;
    }*/
}
