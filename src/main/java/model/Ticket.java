package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    private int id;
    private String url;
    private String subject;
    private String description;
    private long requester_id;
    private String created_at;
    private long assignee_id;
    private String[] tags;

    public Ticket() {
        super();
    }

    public Ticket(int id, String url, String subject, String description, long requester_id, String created_at, long assignee_id, String[] tags) {
        this.id = id;
        this.url = url;
        this.subject = subject;
        this.description = description;
        this.requester_id = requester_id;
        this.created_at = created_at;
        this.assignee_id = assignee_id;
        this.tags = tags;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(long requester_id) {
        this.requester_id = requester_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public long getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(long assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String ticketDetailsById() {
        return "Ticket ID: " + id + "\n" + "URL: "+ url + "\n" + "Subject: " + subject + "\n" + "Description: " + description + "\n" + "Requested By: " + requester_id + "\n" + 
        "Created On: " + created_at + "\n"  + "Assignee ID: " + assignee_id + "\n" + "Tags: " + Arrays.toString(tags) + "\n";
    }

    @Override
    public String toString() {
        return "Ticket ID: " + id + "\n" + "Subject: " + subject + "\n" + "Created On: " + created_at + "\n" + "Requested By: " + requester_id + "\n";
    }
}
