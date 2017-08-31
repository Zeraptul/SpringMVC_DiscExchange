package com.discExchange.vm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by adMin on 14.08.2017.
 */

@Getter
@Setter
@NoArgsConstructor
public class Menu {

    public static final String ACTIVE = "active";
    public static final String MODEL_NAME = "menu";

    private String addDisc;
    private String login;
    private String allDiscs;

    private String takenDiscAll;
    private String takenDiscAllAvailable;
    private String takenDiscAllUserTaken;
    private String takenDiscAllTakenFromUser;

}
