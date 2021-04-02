package insa.demo.resources.users;

import insa.demo.item.Item;
import insa.demo.item.ItemRepository;
import insa.demo.resources.watchlists.WatchListInput;
import insa.demo.resources.watchlists.WatchInput;
import insa.demo.user.User;
import insa.demo.user.UserRepository;
import insa.demo.watchlist.WatchList;
import insa.demo.watchlist.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserResource
 * 
 * @author  Birkan Yildiz & Nicolas Martin
 * @version 1.0
 */

@Path("users")
public class UserResource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WatchListRepository watchListRepository;
    @Autowired
    private ItemRepository itemRepository;

    /** 
     * Obtention de tous les users
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    /** 
     * Création d'un user
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createItem(User u) {
        return userRepository.save(u);
    }

    /** 
     * Modification du mot de passe d'un user
     */
    @PATCH
    @Path("{idUser}/{mdp}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(@PathParam("idUser") Long idUser,@PathParam("mdp") String password) {
        Optional<User> pOpt = userRepository.findById(idUser);

        if(!pOpt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        User u = pOpt.get();
        u.setPassword(password);
        userRepository.save(u);
        return Response.ok(u).build();
    }

    /** 
     * Obtention de toutes les watchlists d'un user
     */
    @GET
    @Path("/{idUser}/watchlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWatchList(@PathParam("idUser") Long idUser) {
        Optional<User> pOpt = userRepository.findById(idUser);

        if(!pOpt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        User u = pOpt.get();

        List<WatchList> l = new ArrayList<>();
        for(WatchList w : u.getWatchLists()){
            if(!l.contains(w))l.add(w);
        }

        return Response.ok(l).build();
    }

    /** 
     * Ajout d'une nouvelle watchlist à un user
     */
    @POST
    @Path("{idUser}/watchlists/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWatchList(@PathParam("idUser") Long idUser, WatchListInput watchListInput) {
        Optional<User> pOpt = userRepository.findById(idUser);

        if(!pOpt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        User u = pOpt.get();
        WatchList w = new WatchList(watchListInput.getName(), watchListInput.getDescription(), new ArrayList<>());

        watchListRepository.save(w);

        if(u.getWatchLists() == null) {
            u.setWatchLists(new ArrayList<WatchList>());
        }

        u.addListe(w);
        userRepository.save(u);
        return Response.ok(u).build();
    }

    /** 
     * Suppression d'une watchlist d'un user
     */
    @DELETE
    @Path("delete/{idUser}/watchlists/{idWatch}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteWatchList(@PathParam("idUser") Long idUser, @PathParam("idWatch") Long idWatch) {
        Optional<WatchList> pOpt = watchListRepository.findById(idWatch);
        Optional<User> lOpt = userRepository.findById(idUser);
        if(!lOpt.isPresent() || !pOpt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        WatchList w = pOpt.get();
        User u = lOpt.get();

        ArrayList<Item> watch = new ArrayList<>();
        itemRepository.findAll().forEach(watch::add);
        for(Item i : watch) {
            w.getListItems().remove(i);
            itemRepository.save(i);
        }
        watchListRepository.save(w);
        w = watchListRepository.findById(idWatch).get();
        u.getWatchLists().remove(w);
        userRepository.save(u);

        try {
            watchListRepository.deleteById(idWatch);
        } catch (Exception e) {
            return Response.serverError().build();
        }

        return Response.noContent().build();
    }


}
