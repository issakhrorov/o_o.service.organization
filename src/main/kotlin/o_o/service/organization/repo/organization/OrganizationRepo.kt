package o_o.service.organization.repo.organization

import o_o.service.organization.model.organization.Organization
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepo: KRepository<Organization, Long> {
  fun findAllByDeletedFalse(pageable: Pageable): Page<Organization>
  fun findByAccountIdAndDeletedFalse(accountId: Long): Organization?
  fun findAllByStatusAndDeletedFalse(status: Int, pageable: Pageable): Page<Organization>
}