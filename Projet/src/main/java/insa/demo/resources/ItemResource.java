package insa.demo.resources;

import insa.demo.item.Item;
import insa.demo.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("items")
public class ItemResource {
    @Autowired
    private ItemRepository itemRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Item> getLivres() {
        ArrayList<Item> livres = new ArrayList<>();
        itemRepository.findAll().forEach(livres::add);
        return livres;
    }
}
