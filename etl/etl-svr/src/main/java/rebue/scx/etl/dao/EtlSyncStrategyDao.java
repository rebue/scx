package rebue.scx.etl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rebue.scx.etl.jo.EtlSyncStrategyJo;

public interface EtlSyncStrategyDao extends JpaRepository<EtlSyncStrategyJo, java.lang.Long> {
}
