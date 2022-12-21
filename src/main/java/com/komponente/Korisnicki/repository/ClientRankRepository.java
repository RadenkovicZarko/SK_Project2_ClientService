package com.komponente.Korisnicki.repository;

import com.komponente.Korisnicki.model.ClientRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRankRepository extends JpaRepository<ClientRank, Long> {
}
