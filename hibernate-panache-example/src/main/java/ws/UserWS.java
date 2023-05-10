package ws;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import entities.Channel;
import entities.User;

@Path("/user")
@Transactional
// 1 - Podemos delegar o controle de transação utilizando a anotação
// @Transactional nos métodos ou no nível da classe.
public class UserWS {

    /// User section

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public User save(@FormParam("hash") String hash, @FormParam("name") String name) {
        User user = new User();
        user.setHash(hash);
        user.setName(name);
        // 2 - O método do Panache `persist` possibilita persistir um objeto.
        user.persist();
        return user;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<User> list() {
        // 3 - O método `listAll` recupera todos os objetos da classe User.
        return User.listAll();
    }

    @GET
    @Path("/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User list(@PathParam("id") Long id) {
        // 4 - O método do Panache `findById` recupera um objeto da classe User.
        return User.findById(id);
    }

    // @POST
    // @Consumes("application/x-www-form-urlencoded")
    // public void getUser(MultivaluedMap<String, String> formParams) {
    //     return formParams.getFirst("userName");
    // }

    /// Channel section

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void createChannel(@FormParam("hash") String channelHash) {
        Channel channel = new Channel();
        
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void enterChannel(@FormParam("userName") String userHash, @FormParam("userName") String channelHash) {
        
    }
    
}