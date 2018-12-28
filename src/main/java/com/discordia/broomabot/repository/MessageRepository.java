package com.discordia.broomabot.repository;

import com.discordia.broomabot.model.MessageModel;
import com.discordia.broomabot.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Long> {
    Collection<MessageModel> findAllByUserAndDeletedFalse(UserModel user);

    @Modifying
    @Query("update MessageModel m set m.deleted=true where m.user=?1")
    void setDeletedByUser(UserModel user);
}
