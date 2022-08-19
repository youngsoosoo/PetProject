package com.ff.furry_friend.repository;

import com.ff.furry_friend.entity.user;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {
    private final EntityManager em;

    public MemoryUserRepository(EntityManager em) {
        this.em = em;
    }

    private static int sequence = 0;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public user save(user user) {
        user.setCreate_id(sequence++);
        user.setCreate_time(sdf.format(timestamp));
        em.persist(user);
        return user;
    }

    @Override
    public Optional<user> findByCreate_Id(Long id) {
        user user = em.find(user.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<user> findAll() {
        return em.createQuery("select m from user m", user.class)
                .getResultList();
    }

    @Override
    public Optional<user> findById(String id) {
        List<user> result = em.createQuery("select m from user m where m.id = :id", user.class)
                .setParameter("id", id)
                .getResultList();
        return result.stream().findAny();
    }

}
