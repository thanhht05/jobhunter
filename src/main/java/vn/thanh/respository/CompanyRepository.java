package vn.thanh.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.thanh.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
