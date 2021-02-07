package br.com.ntconsult.DevBackNTSicredi.repository;

import br.com.ntconsult.DevBackNTSicredi.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
