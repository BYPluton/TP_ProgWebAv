package insa.demo.user;

import insa.demo.watchlist.WatchList;

import javax.persistence.*;
import java.util.List;

/**
 * User
 * Entité stockant les données d'un utilisateur
 * @author  Birkan Yildiz & Nicolas Martin
 * @version 1.0
 */

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pseudo;
    private String password;
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<WatchList> watchLists;

    public User() {
        super();
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

    public void addListe(WatchList l){this.watchLists.add(l);}
}
