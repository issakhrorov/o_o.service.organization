package o_o.service.organization.external.service

import o_o.service.organization.external.dto.AccountDTO
import org.springframework.stereotype.Service

@Service
class AccountService {
  fun getById(id: Long): AccountDTO {
    return AccountDTO()
  }
}