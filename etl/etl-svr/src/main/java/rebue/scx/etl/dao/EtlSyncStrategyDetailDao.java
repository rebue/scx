package rebue.scx.etl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rebue.scx.etl.jo.EtlSyncStrategyDetailJo;

public interface EtlSyncStrategyDetailDao extends JpaRepository<EtlSyncStrategyDetailJo, java.lang.Long> {
}
