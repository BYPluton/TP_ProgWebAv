package insa.demo.user;

import insa.demo.watchlist.WatchList;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String pseudo;
    private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    private List<WatchList> watchLists;

    public User() {
        super();
    }

    public User(String name, String pseudo, String password, List<WatchList> watchLists) {
        this.name = name;
        this.pseudo = pseudo;
        this.password = password;
        this.watchLists = watchLists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<WatchList> getWatchLists() {
        return watchLists;
    }

    public void setWatchLists(List<WatchList> watchLists) {
        this.watchLists = watchLists;
    }

}
