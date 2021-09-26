package rebue.scx.etl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rebue.scx.etl.jo.EtlConnJo;

public interface EtlConnDao extends JpaRepository<EtlConnJo, java.lang.Long> {
}
