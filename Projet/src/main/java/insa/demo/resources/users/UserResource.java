package insa.demo.resources.users;

import insa.demo.resources.watchlists.WatchListInput;
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

    @GET
    @Path("/{idUser}/watchlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWatchList(@PathParam("idUser") Long idUser) {
        Optional<User> pOpt = userRepository.findById(idUser);

        if(!pOpt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        User u = pOpt.get();
        return Response.ok(u.getWatchLists()).build();
    }

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

        u.getWatchLists().add(w);
        userRepository.save(u);
        return Response.ok(u).build();
    }

    @POST
    @Path("{idUser}/add/{idWatch}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWatchList(@PathParam("idUser") Long idUser, @PathParam("idWatch") Long idWatch) {
        Optional<User> pOpt = userRepository.findById(idUser);
        Optional<WatchList> wOpt = watchListRepository.findById(idWatch);

        if(!pOpt.isPresent() || !wOpt.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        User u = pOpt.get();
        WatchList w = wOpt.get();

        if(u.getWatchLists() == null) {
            u.setWatchLists(new ArrayList<WatchList>());
        }

        u.getWatchLists().add(w);
        userRepository.save(u);
        return Response.ok(u).build();
    }


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
