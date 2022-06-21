package com.users.service;

import com.users.entities.Address;
import com.users.entities.Email;
import com.users.entities.User;
import com.users.model.UserModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserModelConverter implements Converter<User, UserModel> {

    @Override
    public UserModel convert(final User user) {
        final UserModel userModel = new UserModel();
        userModel.setId(String.valueOf(user.getId()));
        userModel.setName(user.getFullName());
        userModel.setPin(user.getPin());
        final Address address = user.getAddressList().get(0);
        userModel.setAddrType(address.getAddrType());
        userModel.setAddrInfo(address.getAddrInfo());
        final Email email = user.getEmailList().get(0);
        userModel.setEmailType(email.getEmailType());
        userModel.setEmail(email.getEmail());

        return userModel;
    }
}
