package insa.demo.watchlist;

import insa.demo.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WatchList
 * Entité stockant les données d'une watchlist
 * @author  Birkan Yildiz & Nicolas Martin
 * @version 1.0
 */

@Entity
public class WatchList {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Item> listItems;

    public WatchList() {}

    public WatchList(String name, String description, List<Item> listItems) {
        this.name = name;
        this.description = description;
        this.listItems = listItems;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Item> getListItems() {
        return listItems;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchList watchList = (WatchList) o;
        return Objects.equals(id, watchList.id);
    }

    public void pop(){
        this.setListItems(new ArrayList<>());
    }
}
