package model;

public class Message {
    private String from;
    private String to;
    private String message;
    
    public Message() {
        this.from = "";
        this.to = "";
        this.message = "";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String field, Object value) {
        switch (field) {
            case "from":
                this.from = (String) value;
                break;
            case "to":
                this.to = (String) value;
                break;
            case "message":
                this.message = (String) value;
                break;
            default:
                break;
        }
    }
}
