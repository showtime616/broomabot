package com.discordia.broomabot.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserModel extends AbstractModel {
    private String firstName;

    private String lastName;

    private String userName;

    private boolean chatStarted;

    @OneToMany(mappedBy = "user")
    private Set<MessageModel> messages = new HashSet<>();

    public UserModel() {
    }

    public UserModel(final Long id, String firstName, String lastName, String userName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isChatStarted() {
        return chatStarted;
    }

    public void setChatStarted(boolean chatStarted) {
        this.chatStarted = chatStarted;
    }

    public Set<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageModel> messages) {
        this.messages = messages;
    }
}
