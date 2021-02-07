package br.com.ntconsult.DevBackNTSicredi.repository;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import br.com.ntconsult.DevBackNTSicredi.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT COUNT(*) FROM Vote v WHERE v.value=0 AND v.session=:session")
    int findYesCountBySessionId(@Param("session") Session sessionId);

    @Query("SELECT COUNT(*) FROM Vote v WHERE v.value=1 AND v.session=:session")
    int findNoCountBySessionId(@Param("session") Session sessionId);
}
