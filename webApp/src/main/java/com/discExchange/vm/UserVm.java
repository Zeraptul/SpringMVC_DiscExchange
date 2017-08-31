package com.discExchange.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by adMin on 09.08.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVm {

    public static final String GENDER_MALE ="M";
    public static final String GENDER_FEMALE = "F";

    public static final String MODEL_NAME = "userVm";

    private String name;
    private String password;
    private String gender;
    private int id;
}
