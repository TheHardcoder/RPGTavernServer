package de.byedev.rpgtavern.webapi.dto;

import de.byedev.rpgtavern.persistence.entities.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name="Users")
public class UsersDTO {

    private Collection<UserDTO> users;

    public UsersDTO(Collection<UserDTO> users) {
        this.users = users;
    }

    @XmlElement(name="Item")
    public Collection<UserDTO> getUsers() {
        return users;
    }
}
