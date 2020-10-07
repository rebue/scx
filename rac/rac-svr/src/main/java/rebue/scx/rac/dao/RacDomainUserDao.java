package rebue.scx.rac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rebue.scx.rac.jo.RacDomainUserJo;

public interface RacDomainUserDao extends JpaRepository<RacDomainUserJo, java.lang.Long> {
}
