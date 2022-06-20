package com.users.service;

import com.users.entities.Address;
import com.users.entities.Email;
import com.users.entities.User;
import com.users.model.UserModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserToUserModelConverter implements Converter<User, UserModel> {

    @Override
    public UserModel convert(final User user) {
        final UserModel userModel = new UserModel();
        final String[] name = user.getFullName().split(" ");
        userModel.setId(String.valueOf(user.getId()));
        userModel.setFirstName(name[0]);
        userModel.setLastName(name[1]);
        userModel.setPin(user.getPin());
        final List<Address> addressList = user.getAddressList();
        if (!addressList.isEmpty()) {
            final Address address = addressList.get(0);
            userModel.setAddrType(address.getAddrType());
            userModel.setAddrInfo(address.getAddrInfo());
        }

        final List<Email> emailList = user.getEmailList();
        if (!emailList.isEmpty()) {
            final Email email = emailList.get(0);
            userModel.setEmailType(email.getEmailType());
            userModel.setEmail(email.getEmail());
        }
        return userModel;
    }
}
