package com.users.service;

import com.users.entities.Address;
import com.users.entities.Email;
import com.users.entities.User;
import com.users.model.UserModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserModelService {

    private final UserService userService;
    private final Converter<User, UserModel> userConverter;

    public UserModelService(final UserService userService,
                            final Converter<User, UserModel> userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    public UserModel findById(final Long userId) {
        final User user = userService.findById(userId);
        return userConverter.convert(user);
    }

    public List<UserModel> findAll() {
        final List<UserModel> userModels = new ArrayList<>();
        userService.findAll().forEach(user -> userModels.add(userConverter.convert(user)));
        return userModels;
    }

    public List<UserModel> findByFullName(final String name) {
        return userService.findByFullName(name).stream()
                .map(userConverter::convert)
                .collect(Collectors.toList());
    }

    public void save(final UserModel userModel) {

        final User user = new User(getFullName(userModel), userModel.getPin());
        user.getAddressList().add(new Address(userModel.getAddrType(), userModel.getAddrInfo()));
        user.getEmailList().add(new Email(userModel.getEmailType(), userModel.getEmail()));
        userService.save(user);
    }

    public void update(final UserModel userModel) {
        final Long userId = Long.parseLong(userModel.getId());
        final User user = userService.findById(userId);
        user.setFullName(getFullName(userModel));
        user.setPin(userModel.getPin());
        user.getAddressList().clear();
        user.getEmailList().clear();
        user.getAddressList().add(new Address(
                userModel.getAddrType(),
                userModel.getAddrInfo()));
        user.getEmailList().add(new Email(
                userModel.getEmailType(),
                userModel.getEmail()));
        userService.save(user);
    }

    public void delete(final Long userId) {
        final User user = userService.findById(userId);
        userService.delete(user);
    }

    private String getFullName(final UserModel userModel) {
        return userModel.getFirstName().trim() + " " + userModel.getLastName().trim();
    }
}
