package com.planetsystems.tela.Models;

public class SystemUsers {

    int id;
    String database_id;
    String employeeNumber;
    String firstName;
    String lastName;
    String role;
    String school_id;
    String schoolName;
    String device_id;

    // setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatabase_id() {
        return database_id;
    }

    public String setDatabase_id(String database_id) {
        this.database_id = database_id;
        return database_id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        return employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String setFirstName(String firstName) {
        this.firstName = firstName;
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String setLastName(String lastName) {
        this.lastName = lastName;
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String setRole(String role) {
        this.role = role;
        return role;
    }

    public String getSchool_id() {
        return school_id;
    }

    public String setSchool_id(String school_id) {
        this.school_id = school_id;
        return school_id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String setSchoolName(String schoolName) {
        this.schoolName = schoolName;
        return schoolName;
    }

    public String getDevice_id() {
        return device_id;
    }

    public String setDevice_id(String device_id) {
        this.device_id = device_id;
        return device_id;
    }


    public static final String TABLE_USERS = "System_users";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATABASE_ID = "database_id";
    public static final String COLUMN_EMPLOYEE_NUMBER = "employeeNumber";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_SCHOOL_ID = "school_id";
    public static final String COLUMN_SCHOOL_NAME = "schoolName";
    public static final String COLUMN_DEVICE_ID = "device_id";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "create table " + TABLE_USERS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DATABASE_ID + " TEXT,"
                    + COLUMN_EMPLOYEE_NUMBER + " TEXT"
                    + COLUMN_FIRST_NAME + "TEXT,"
                    + COLUMN_LAST_NAME + "TEXT,"
                    + COLUMN_ROLE + "TEXT,"
                    + COLUMN_SCHOOL_ID + "TEXT,"
                    + COLUMN_SCHOOL_NAME + "TEXT,"
                    + COLUMN_DEVICE_ID + "INTEGER"
                    + ")";

    //Constructor
    public SystemUsers(){

    }

    public SystemUsers(int id,String database_id, String employeeNumber, String firstName, String lastName, String role,
                       String school_id, String schoolName, String device_id){
        this.id = id;
        this.database_id = database_id;
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.school_id = school_id;
        this.schoolName = schoolName;
        this.device_id = device_id;
    }

}
