package br.com.buzzmonitor.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

@Document(indexName = "posts", type = "post")
public class Post {

    @Id
    private UUID id;
    private String content;
    private String rt;
    private String service;
    private User author;
    private LinkedList<String> urls;
    private String application;
    private String in_reply_to;
    private String language;
    private String created_At;
   // private Date dateFormat;
    private String upload_At;
    private String picture_full_size;
    private LinkedList<String> tag;

    public Post(){
        this.urls = new LinkedList();
        this.tag = new LinkedList();
    }


    //getters and setters
    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getRt() { return rt; }

    public void setRt(String rt) { this.rt = rt; }

    public String getService() { return service; }

    public void setService(String service) { this.service = service; }

    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

    public LinkedList getUrls() { return urls; }

    public void setUrls(LinkedList urls) { this.urls = urls; }

    public String getApplication() { return application; }

    public void setApplication(String application) { this.application = application; }

    public String getIn_reply_to() { return in_reply_to; }

    public void setIn_reply_to(String in_reply_to) { this.in_reply_to = in_reply_to; }

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }

    public String getCreated_At() { return created_At; }

    public void setCreated_At(String created_At) { this.created_At = created_At; }

    public String getUpload_At() { return upload_At; }

    public void setUpload_At(String upload_At) { this.upload_At = upload_At; }

    public String getPicture_full_size() { return picture_full_size; }

    public void setPicture_full_size(String picture_full_size) { this.picture_full_size = picture_full_size; }

    public LinkedList getTag() { return tag; }

    public void setTag(LinkedList tag) { this.tag = tag; }



/*    public Date getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(Date dateFormat) {
        this.dateFormat = dateFormat;
    }*/
}
