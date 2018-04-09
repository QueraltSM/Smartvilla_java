package model;

public class Person {
    private String username;
    private String surname;
    private String email;
    private String password;
    private Object lastVisit;

    public Person() {
        this.username = "";
        this.surname = "";
        this.email = "";
        this.password = "";
        this.lastVisit = 0;
        
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }
    
    public Object getLastVisit() {
        return lastVisit;
    }
    
    public void setPerson(String field, Object value) {
        switch (field) {
            case "name":
                this.username = (String) value;
                break;
            case "surname":
                this.surname = (String) value;
                break;
            case "email":
                this.email = (String) value;
                break;
            case "password":
                this.password = (String) value;
                break;
            case "last_visit":
                this.lastVisit = value;
            default:
                break;
        }
    }
    
}
