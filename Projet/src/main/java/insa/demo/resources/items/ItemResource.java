package insa.demo.resources.items;

import insa.demo.item.Item;
import insa.demo.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("items")
public class ItemResource {
    @Autowired
    private ItemRepository itemRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Item createItem(Item i) {
        return itemRepository.save(i);
    }


}
