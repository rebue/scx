package rebue.scx.rac.dao;import org.springframework.data.jpa.repository.JpaRepository;
import rebue.scx.rac.jo.RacUserJo;



public interface RacUserDao extends JpaRepository<RacUserJo, java.lang.Long> {
}
