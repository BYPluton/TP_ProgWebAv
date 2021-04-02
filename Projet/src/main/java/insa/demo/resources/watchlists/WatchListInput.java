package insa.demo.resources.watchlists;

/**
 * WatchListInput
 * class permettant la création d'une watchlist depuis le front
 * @author  Birkan Yildiz & Nicolas Martin
 * @version 1.0
 */
public class WatchListInput {
    String name;
    String description;

    public WatchListInput() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
