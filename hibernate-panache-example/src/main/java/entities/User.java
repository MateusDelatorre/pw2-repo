package entities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class User extends PanacheEntity {

    private String name;
    private int hash;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    private final Set<Channel> channels;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Message> messages;

    public User() {
        channels = new LinkedHashSet<>();
        messages = new ArrayList<>();
        this.hash = hashCode();
    }

    public String getName(){
        return this.name;
    }

    public int getHash(){
        return this.hash;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Message> getMessage() {
        return messages;
    }
    
    public Set<Channel> getChannel() {
        return channels;
    }

    public void addMessage(final Message message) {
        messages.add(message);
    }
    
    public void addChannel(final Channel channel) {
        channels.add(channel);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, channels, messages);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final User other = (User) obj;
        return Objects.equals(name, other.name) && Objects.equals(channels, other.channels)
                && Objects.equals(messages, other.messages);
    }
}

