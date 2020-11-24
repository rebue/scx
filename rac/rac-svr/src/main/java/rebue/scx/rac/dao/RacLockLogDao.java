package rebue.scx.rac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rebue.scx.rac.jo.RacLockLogJo;

public interface RacLockLogDao extends JpaRepository<RacLockLogJo, java.lang.Long> {
}
