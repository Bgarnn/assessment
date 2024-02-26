package com.kbtg.bootcamp.posttest.UserTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Integer> {
    @Query("SELECT ut.UserTicketId FROM UserTicket ut WHERE ut.UserId = :userId")
    List<String> findUserTicketIdsByUserId(@Param("userId") String userId);

    @Query("SELECT SUM(ut.UserTicketAmount) FROM UserTicket ut WHERE ut.UserId = :userId")
    Integer sumUserTicketAmountByUserId(@Param("userId") String userId);

    @Query("SELECT SUM(ut.UserTicketPrice) FROM UserTicket ut WHERE ut.UserId = :userId")
    Double sumUserTicketPriceByUserId(@Param("userId") String userId);

    @Query("SELECT ut FROM UserTicket ut WHERE ut.UserId = :userId AND ut.UserTicketId = :userTicketId")
    Optional<UserTicket> findByUserIdAndUserTicketId(@Param("userId") String userId, @Param("userTicketId") String userTicketId);
}
