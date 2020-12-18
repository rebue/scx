package rebue.scx.rac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rebue.scx.rac.jo.RacPersonJo;

public interface RacPersonDao extends JpaRepository<RacPersonJo, java.lang.Long> {
}
