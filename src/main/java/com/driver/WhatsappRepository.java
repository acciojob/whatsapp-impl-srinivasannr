package com.driver;


import java.util.*;



import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    /*
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }*/
    int count=0;
    int id=0;
    HashSet<String> setMobile=new HashSet<>();
    HashSet<String> setUser=new HashSet<>();
    HashMap<Group, User> adminMap=new HashMap<>();

    HashMap<Group, List<User>> groupUserMap=new HashMap<>();
    public String createUser(String name, String mobile) throws Exception {
        //If the mobile number exists in database, throw "User already exists" exception
        //Otherwise, create the user and return "SUCCESS"
        if(setMobile.contains(mobile)){
            throw new Exception("User already exists");
        }
        setUser.add(name);
        setMobile.add(mobile);
        return "SUCCESS";
    }

    public Group createGroup(List<User> users){
        Group a;
        count++;
        if(users.size()==2){
            a=new Group(users.get(1).getName(),users.size());
            groupUserMap.put(a,users);
        }
        else{
            a=new Group(users.get(1).getName(),users.size());
            groupUserMap.put(a,users);
            a=new Group("Group"+count,users.size());
            groupUserMap.put(a,users);


        }

        return a;

    }

    public int createMessage(String content){
        // The 'i^th' created message has message id 'i'.
        // Return the message id.
        id++;
        Message m=new Message(id,content,new Date());

        return id;
    }
    HashMap<Group, List<Message>> groupMessageMap=new HashMap<>();
    public int sendMessage(Message message, User sender, Group group) throws Exception{
        //Throw "Group does not exist" if the mentioned group does not exist
        //Throw "You are not allowed to send message" if the sender is not a member of the group
        //If the message is sent successfully, return the final number of messages in that group.
        if(!groupUserMap.containsKey(group)){
            throw new Exception("Group does not exist");
        }

        List<User> l=groupUserMap.get(group);
        if(!l.contains(sender)) {
            throw new Exception("You are not allowed to send message");
        }

        if(groupMessageMap.containsKey(group)){
            List<Message> list=groupMessageMap.get(group);
            list.add(message);
            groupMessageMap.put(group,list);
        }
        else {
            List<Message> m = new ArrayList<>();
            m.add(message);
            groupMessageMap.put(group, m);
        }
        return groupMessageMap.get(group).size();
    }


    public String changeAdmin(User approver, User user, Group group) throws Exception{
        //Throw "Group does not exist" if the mentioned group does not exist
        //Throw "Approver does not have rights" if the approver is not the current admin of the group
        //Throw "User is not a participant" if the user is not a part of the group
        //Change the admin of the group to "user" and return "SUCCESS". Note that at one time there is only one admin and the admin rights are transferred from approver to user.
        if(!groupUserMap.containsKey(group)){
            throw new Exception("Group does not exist");
        }
        if(!adminMap.get(group).equals(approver)){
            throw new Exception("Approver does not have rights");
        }
        if(!groupUserMap.get(group).contains(user)){
            throw new Exception("User is not a participant");
        }
        adminMap.put(group,user);

        return "SUCCESS";

    }

}
