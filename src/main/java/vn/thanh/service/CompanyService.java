package vn.thanh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.thanh.controller.AuthController;
import vn.thanh.domain.Company;
import vn.thanh.domain.dto.Meta;
import vn.thanh.domain.dto.ResultPaginationDTO;
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

    public ResultPaginationDTO handleGetAllCompany(Pageable pageable) {
        Page<Company> companyPage = this.companyRepository.findAll(pageable);

        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta meta = new Meta();

        meta.setPage(companyPage.getNumber() + 1);
        meta.setPageSize(companyPage.getSize());
        meta.setTotal(companyPage.getTotalElements());
        meta.setPages(companyPage.getTotalPages());

        resultPaginationDTO.setMeta(meta);
        resultPaginationDTO.setResult(companyPage.getContent());
        return resultPaginationDTO;
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