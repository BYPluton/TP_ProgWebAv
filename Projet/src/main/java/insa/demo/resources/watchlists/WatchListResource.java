package insa.demo.resources.watchlists;

import insa.demo.item.Item;
import insa.demo.item.ItemRepository;
import insa.demo.user.User;
import insa.demo.user.UserRepository;
import insa.demo.watchlist.WatchList;
import insa.demo.watchlist.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Optional;

/**
 * WatchListResource
 * 
 * @author  Birkan Yildiz & Nicolas Martin
 * @version 1.0
 */

@Path("watchlists")
public class WatchListResource {
    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private ItemRepository itemRepository;

    /** 
     * Obtention de toutes les watchlists
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<WatchList> getWatchLists() {
        ArrayList<WatchList> watchLists = new ArrayList<>();
        watchListRepository.findAll().forEach(watchLists::add);
        return watchLists;
    }

    /** 
     * création d'une watchlist
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WatchList createItem(WatchList u) {
        return watchListRepository.save(u);
    }

    /** 
     * Obtention des items d'une watchlist
     */
    @GET
    @Path("{idWatch}/items/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems(@PathParam("idWatch") Long idWatch, @PathParam("idItem") Long idItem) {
        Optional<WatchList> pOpt = watchListRepository.findById(idWatch);
        if(!pOpt.isPresent())
            return Response.status(Response.Status.NOT_FOUND).build();

        WatchList w = pOpt.get();
        return Response.ok(w.getListItems()).build();
    }

    /** 
     * Suppression d'un anime d'une watchlist
     */
    @DELETE
    @Path("{idWatch}/items/{idItem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("idWatch") Long idWatch, @PathParam("idItem") Long idItem) {
        Optional<WatchList> pOpt = watchListRepository.findById(idWatch);
        Optional<Item> lOpt = itemRepository.findById(idItem);

        if(!lOpt.isPresent() || !pOpt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        WatchList w = pOpt.get();
        Item i = lOpt.get();

        w.getListItems().remove(i);
        watchListRepository.save(w);
        return Response.ok(w).build();
    }

    /** 
     * Ajout d'un anime d'une watchlist
     */
    @POST
    @Path("{idWatch}/items/{idItem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(@PathParam("idWatch") Long idWatch, @PathParam("idItem") Long idItem) {
        Optional<WatchList> pOpt = watchListRepository.findById(idWatch);
        Optional<Item> lOpt = itemRepository.findById(idItem);

        if(!lOpt.isPresent() || !pOpt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        WatchList w = pOpt.get();
        Item i = lOpt.get();

        if(w.getListItems() == null) {
            w.setListItems(new ArrayList<Item>());
        }

        w.getListItems().add(i);
        watchListRepository.save(w);
        return Response.ok(w).build();
    }

}
