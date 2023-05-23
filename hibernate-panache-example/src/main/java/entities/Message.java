package entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Message {
    private String text;

    @Column(name = "channel_id")
    private Long channelId;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public int hashCode() {
        return Objects.hash(text, channelId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        return Objects.equals(text, other.text) && 
            Objects.equals(channelId, other.channelId)
            && Objects.equals(userId, other.userId);
    }
}
