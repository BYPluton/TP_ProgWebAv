package insa.demo.resources.users;

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
import java.util.Optional;

@Path("users")
public class UserResource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WatchListRepository watchListRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createItem(User u) {
        return userRepository.save(u);
    }

    @GET
    @Path("/{idUser}/watchlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWatchListExistant(@PathParam("idUser") Long idUser) {
        Optional<User> pOpt = userRepository.findById(idUser);

        if(!pOpt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        User u = pOpt.get();

        if(u.getWatchLists() == null) {
            u.setWatchLists(new ArrayList<>());
        }

        var list = u.getWatchLists();

        return Response.ok(list).build();
    }

    @POST
    @Path("{idUser}/watchlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWatchListExistant(@PathParam("idUser") Long idUser, WatchInput idWatch) {
        Optional<User> pOpt = userRepository.findById(idUser);
        Optional<WatchList> lOpt = watchListRepository.findById(idWatch.getIdWatch());

        if(!lOpt.isPresent() || !pOpt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        User u = pOpt.get();
        WatchList w = lOpt.get();

        if(u.getWatchLists() == null) {
            u.setWatchLists(new ArrayList<WatchList>());
        }

        u.getWatchLists().add(w);
        userRepository.save(u);
        return Response.ok(u).build();
    }



}
