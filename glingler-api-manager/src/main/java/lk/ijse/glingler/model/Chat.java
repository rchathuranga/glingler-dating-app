package lk.ijse.glingler.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "chat")
public class Chat {
    private int chatId;
    private Matched matchedId;
    private int chatSendProfileId;
    private String message;
    private Timestamp createdTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", nullable = false)
    public int getChatId() {
        return chatId;
    }
    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "matched_id")
    public Matched getMatchedId() {
        return matchedId;
    }
    public void setMatchedId(Matched matchedId) {
        this.matchedId = matchedId;
    }

    @Basic
    @Column(name = "chat_send_profile_id")
    public int getChatSendProfileId() {
        return chatSendProfileId;
    }
    public void setChatSendProfileId(int chatSendProfileId) {
        this.chatSendProfileId = chatSendProfileId;
    }

    @Basic
    @Column(name = "message", nullable = true, length = -1)
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "created_time", nullable = true)
    public Timestamp getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return chatId == chat.chatId &&
                Objects.equals(message, chat.message) &&
                Objects.equals(createdTime, chat.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, message, createdTime);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", matchedId=" + matchedId +
                ", message='" + message + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
