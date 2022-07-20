package main.repositories;

import main.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query("SELECT m FROM Message m")
    Page<Message> findMessages(Pageable pageable);

    @Query("SELECT m FROM Message m WHERE m.user.userId =:userId")
    Optional<List<Message>> findMessageByUserId(Integer userId);
}
