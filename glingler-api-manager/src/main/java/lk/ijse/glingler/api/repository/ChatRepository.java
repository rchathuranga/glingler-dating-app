package lk.ijse.glingler.api.repository;

import lk.ijse.glingler.model.Chat;
import lk.ijse.glingler.model.Matched;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    public List<Chat> getAllChatsByMatchedId(Matched matched);

}
