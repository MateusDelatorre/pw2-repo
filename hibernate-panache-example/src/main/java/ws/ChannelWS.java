package ws;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import entities.Channel;
import entities.User;

@Path("/channels")
public class ChannelWS {
    
    @POST
    @Path("/create")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Channel createChannel(@FormParam("channnelName") String name, @FormParam("userId") Long id) {
        Channel channel = new Channel();
        User user = User.findById(id);
        user.addChannel(channel);
        channel.addUser(user);
        channel.persist();
        user.persist();
        return channel;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Channel addUserToChannel(@FormParam("channelId") Long channelId, @FormParam("userId") Long userId) {
        Channel channel = Channel.findById(channelId);
        User user = User.findById(userId);
        user.addChannel(channel);
        user.persist();
        channel.addUser(user);
        return channel;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Channel getChannel(@PathParam("id") Long id) {
        return Channel.findById(id);
    }
}
