package vn.thanh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import vn.thanh.controller.AuthController;
import vn.thanh.domain.Company;
import vn.thanh.respository.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;

    }

    public Company handleSaveCompany(Company company) {
        return this.companyRepository.save(company);

    }

    public List<Company> handleGetAllCompany() {
        return this.companyRepository.findAll();
    }

    public void handleDeleteCompanyById(long id) {
        this.companyRepository.deleteById(id);
    }

    public Company handleUpdateCompany(Company c) {
        Optional<Company> companyOptional = this.companyRepository.findById(c.getId());

        if (companyOptional.isPresent()) {
            Company currentCompany = companyOptional.get();

            currentCompany.setAddress(c.getAddress());
            currentCompany.setName(c.getName());
            currentCompany.setLogo(c.getLogo());
            currentCompany.setDescription(c.getDescription());

            return this.companyRepository.save(currentCompany);

        }
        return null;

    }

}