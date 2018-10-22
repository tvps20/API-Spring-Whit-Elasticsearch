package br.com.buzzmonitor.models;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.UUID;

@Document(indexName = "usuarios", type = "users")
public class User {

    @Id
    private UUID id;
    @NotEmpty
    private String nome;
    @NotEmpty @UniqueElements
    private String username;

    private String password;
    @NotEmpty
    private boolean admin;
    private String descricao;
    private LinkedList<String> bios;
    private int followers;
    private int friends;
    private String location;
    private String gender;
    private String created_at;
    private String update_at;
    private String profile_image_url;
    private String tweets;

    public User(){
        this.followers = 0;
        this.friends = 0;
        this.setBios(new LinkedList());
    }


    //getters and setters
    public UUID getId() { return id; }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() { return admin; }

    public void setAdmin(boolean admin) { this.admin = admin; }

    public String getCreated_at() { return created_at; }

    public void setCreated_at(String created_at) { this.created_at = created_at; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LinkedList getBios() { return bios; }

    public void setBios(LinkedList<String> bios) { this.bios = bios; }

    public int getFollowers() { return followers; }

    public void setFollowers(int followers) { this.followers = followers; }

    public int getFriends() { return friends; }

    public void setFriends(int friends) { this.friends = friends; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getProfile_image_url() { return profile_image_url; }

    public void setProfile_image_url(String profile_image_url) { this.profile_image_url = profile_image_url; }

    public String getTweets() { return tweets; }

    public void setTweets(String tweets) { this.tweets = tweets; }

    public String getUpdate_at() { return update_at; }

    public void setUpdate_at(String update_at) { this.update_at = update_at; }
}
