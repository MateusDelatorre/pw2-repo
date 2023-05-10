package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class User extends PanacheEntity {

    private String name;
    private String hash;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // name = nome da coluna que irá armazenar a chave estrangeira
    // na tabela Message
    @JoinColumn(name = "user_id")
    private List<Message> messages;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Channel> channels;

    public User() {
        this.messages = new ArrayList<>();
        this.channels = new ArrayList<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setHash(String hash){
        this.hash = hash;
    }
}

