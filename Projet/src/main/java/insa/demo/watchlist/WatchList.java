package insa.demo.watchlist;

import insa.demo.item.Item;
import insa.demo.user.User;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class WatchList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    private ArrayList<Item> listItems;

    public WatchList() {}

    public WatchList(String name, User user, ArrayList<Item> listItems) {
        this.name = name;
        this.user = user;
        this.listItems = listItems;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Item> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<Item> listItems) {
        this.listItems = listItems;
    }
}
