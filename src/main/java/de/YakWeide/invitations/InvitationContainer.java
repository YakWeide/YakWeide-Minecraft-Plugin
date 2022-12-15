package de.YakWeide.invitations;

import java.util.ArrayList;

public class InvitationContainer {
    private static ArrayList<Invitation> invitations;
    private static InvitationContainer instance;
    private InvitationContainer(){};

    public static InvitationContainer getInstance(){
        if(instance == null){
            instance = new InvitationContainer();
        }
        return instance;
    }

    public static void addInvitation()

}
