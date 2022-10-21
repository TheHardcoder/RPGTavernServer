package de.byedev.rpgtavern.webapi.dto;

import de.byedev.rpgtavern.persistence.entities.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class UserDTO {

    private String name;
    private String email;
    private String lastGame;
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String lastGame, List<String> roles) {
        this.name = name;
        this.email = email;
        this.lastGame = lastGame;
        this.roles = roles;
    }

    public static UserDTO fromUser(User user) {
        if (user == null)
            return null;
        UserDTO userDTO = new UserDTO();
        userDTO.name = user.getUsername();
        userDTO.email = user.getEmail();
        userDTO.roles = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
        userDTO.lastGame = user.getLastGame();
        return userDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastGame() {
        return lastGame;
    }

    public void setLastGame(String lastGame) {
        this.lastGame = lastGame;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
