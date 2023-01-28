package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Collections;

@Service
public class WhatsappService {
    //@Autowired
    WhatsappRepository whatsappRepository=new WhatsappRepository();
    public String createUser(String name, String mobile) throws Exception {
        //If the mobile number exists in database, throw "User already exists" exception
        //Otherwise, create the user and return "SUCCESS"

        return whatsappRepository.createUser(name, mobile);
    }

    public Group createGroup(List<User> users){

        return whatsappRepository.createGroup(users);

    }

    public int createMessage(String content){
        // The 'i^th' created message has message id 'i'.
        // Return the message id.

        return whatsappRepository.createMessage(content);
    }
    public int sendMessage(Message message, User sender, Group group) throws Exception{
        //Throw "Group does not exist" if the mentioned group does not exist
        //Throw "You are not allowed to send message" if the sender is not a member of the group
        //If the message is sent successfully, return the final number of messages in that group.

        return whatsappRepository.sendMessage(message, sender, group);
    }
    public String changeAdmin(User approver, User user, Group group) throws Exception{
        //Throw "Group does not exist" if the mentioned group does not exist
        //Throw "Approver does not have rights" if the approver is not the current admin of the group
        //Throw "User is not a participant" if the user is not a part of the group
        //Change the admin of the group to "user" and return "SUCCESS". Note that at one time there is only one admin and the admin rights are transferred from approver to user.

        return whatsappRepository.changeAdmin(approver, user, group);
    }

}
