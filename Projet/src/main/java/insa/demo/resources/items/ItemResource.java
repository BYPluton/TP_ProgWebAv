package insa.demo.resources.items;

import insa.demo.item.Item;
import insa.demo.item.ItemRepository;
import insa.demo.user.User;
import insa.demo.watchlist.WatchList;
import insa.demo.watchlist.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Optional;


/**
 * ItemResource
 * 
 * @author  Birkan Yildiz & Nicolas Martin
 * @version 1.0
 */

@Path("items")
public class ItemResource {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private WatchListRepository watchListRepository;

    /** 
     * Obtention de tous les items
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    /** 
     * Creation d'un item
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Item createItem(Item i) {
        return itemRepository.save(i);
    }

    /** 
     * Suppression d'un item par son id
     */
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id") Long id) {
        Optional<Item> iOpt = itemRepository.findById(id);
        if(!iOpt.isPresent())
            return Response.status(Response.Status.NOT_FOUND).build();

        Item i = iOpt.get();
        ArrayList<WatchList> watch = new ArrayList<>();
        watchListRepository.findAll().forEach(watch::add);
        for(WatchList w : watch) {
            w.getListItems().remove(i);
            watchListRepository.save(w);
        }

        try {
            itemRepository.deleteById(id);
        } catch (Exception e) {
            return Response.serverError().build();
        }

        return Response.noContent().build();
    }
}
