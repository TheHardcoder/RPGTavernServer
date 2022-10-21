package de.byedev.rpgtavern.core;

import de.byedev.rpgtavern.core.exceptions.UserCreationException;
import de.byedev.rpgtavern.core.util.EmailUtils;
import de.byedev.rpgtavern.persistence.Storage;
import de.byedev.rpgtavern.persistence.entities.User;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.*;

@Component(service = UserService.class)
public class UserService {

    @Reference
    private Storage storage;

    public Collection<User> getUsers()
    {
        return storage.getRoot().getUsers();
    }

    public Optional<User> findUserByEmail(String email) {
        return getUsers().stream().filter(u -> u.getEmail() != null && u.getEmail().equals(email)).findAny();
    }


    public void createAccount(String name, String email, char[] password) throws UserCreationException
    {
        Optional<User> user = findUserByEmail(email);
        if (user.isEmpty())
        {
            if (name == null || name.length() < 3) throw new UserCreationException("Invalid name: " + name);
            if (!EmailUtils.isValidEmailAddress(email)) throw new UserCreationException("Invalid email: " + email);
            if (password == null || password.length < 8) throw new UserCreationException("Invalid password: " + password);
            User newUser = new User(name,email,password);
            storage.getRoot().addUser(newUser);
            storage.storeRoot();
            storage.store(storage.getRoot().getUsers());
        }
//        login(email,password);
    }
}
