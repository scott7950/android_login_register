package com.example.scott.login_register;

/**
 * Created by Scott on 7/16/2015.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String name, username, password;
    private int age;
    private int userType;

    public User() {
        userType = -1;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.age = -1;
        this.name = "";
    }

    public User (String username, String password, String name, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public User (String username, String password, String name, int age, int userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.userType = userType;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getUserType() {
        return this.userType;
    }

    public void update (User user) {
        this.username = user.username;
        this.password = user.password;
        this.name = user.name;
        this.age = user.age;
        this.userType = user.userType;
    }

    public void update (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void update (String name, int age, int userType) {
        this.name = name;
        this.age = age;
        this.userType = userType;
    }

    public void update (String username, String password, String name, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public void update (String username, String password, String name, int age, int userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.userType = userType;
    }

    public boolean isValidUsernamePattern() {
        return isValidUsernamePattern(username);
    }

    public boolean isValidUsernamePattern(String username) {
        String str = username;

        //remove space in the begining and end
        str.replace("^\\s+", "");
        str.replace("\\s+$", "");


        String pattern = "\\s";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        if(m.find()) {
            return false;
        }

        if(str.length() == 0) {
            return false;
        }

        return true;

        /*
        for(int i=1; i<5; i++) {
            if(Integer.parseInt(m.group(i)) < 0 || Integer.parseInt(m.group(i)) > 255) {
                return false;
            }
        }
        */

    }

    public boolean isValidPasswordPattern() {
        return isValidPasswordPattern(password);
    }

    public boolean isValidPasswordPattern(String password) {
        String str = password;

        //remove space in the begining and end
        str.replace("^\\s+", "");
        str.replace("\\s+$", "");


        String pattern = "\\s";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        if(m.find()) {
            return false;
        }

        if(str.length() == 0) {
            return false;
        }

        return true;

    }

    public boolean isValidNamePattern() {
        return isValidNamePattern(name);
    }

    public boolean isValidNamePattern(String name) {
        String str = name;

        //remove space in the begining and end
        str.replace("^\\s+", "");
        str.replace("\\s+$", "");

        if(str.length() == 0) {
            return false;
        }

        return true;

    }

    public boolean isValidAgePattern(String age) {
        String str = age;

        //remove space in the begining and end
        str.replace("^\\s+", "");
        str.replace("\\s+$", "");


        String pattern = "^\\d+$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        if(m.find()) {
            return true;
        } else {
            return false;
        }

    }

    public void clear() {
        userType = -1;
        username = "";
        password = "";
        name = "";
        age = -1;
    }

}
