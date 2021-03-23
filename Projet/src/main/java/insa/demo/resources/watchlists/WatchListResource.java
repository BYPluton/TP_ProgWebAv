package insa.demo.resources.watchlists;

import insa.demo.item.Item;
import insa.demo.item.ItemRepository;
import insa.demo.resources.items.ItemInput;
import insa.demo.watchlist.WatchList;
import insa.demo.watchlist.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Optional;

@Path("watchlists")
public class WatchListResource {
    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<WatchList> getWatchLists() {
        ArrayList<WatchList> watchLists = new ArrayList<>();
        watchListRepository.findAll().forEach(watchLists::add);
        return watchLists;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WatchList createItem(WatchList u) {
        return watchListRepository.save(u);
    }

    @POST
    @Path("{idWatch}/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWatchListExistant(@PathParam("idWatch") Long idWatch, ItemInput idItem) {
        Optional<WatchList> pOpt = watchListRepository.findById(idWatch);
        Optional<Item> lOpt = itemRepository.findById(idItem.getIdItem());

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
