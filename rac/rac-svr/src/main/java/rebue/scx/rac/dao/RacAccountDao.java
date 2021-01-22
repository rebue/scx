package rebue.scx.rac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rebue.scx.rac.jo.RacAccountJo;

public interface RacAccountDao extends JpaRepository<RacAccountJo, java.lang.Long> {
}
