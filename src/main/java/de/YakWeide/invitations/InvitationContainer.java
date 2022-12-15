package de.YakWeide.invitations;

public class InvitationContainer {
    private
    private static InvitationContainer instance;
    private InvitationContainer(){};

    public static InvitationContainer getInstance(){
        if(instance == null){
            instance = new InvitationContainer();
        }
        return instance;
    }

    public static void addEvent

}
