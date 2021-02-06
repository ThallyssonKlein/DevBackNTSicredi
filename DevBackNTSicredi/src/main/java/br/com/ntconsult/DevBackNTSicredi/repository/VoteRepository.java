package br.com.ntconsult.DevBackNTSicredi.repository;

import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
