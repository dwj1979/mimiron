package cn.mimiron.uaa.repository;

import cn.mimiron.uaa.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 * @author zhangxd
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
