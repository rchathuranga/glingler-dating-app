package lk.ijse.glingler.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Chat {
    private int chatId;
    private String message;
    private Timestamp createdTime;

    @Id
    @Column(name = "chat_id", nullable = false)
    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
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
}
