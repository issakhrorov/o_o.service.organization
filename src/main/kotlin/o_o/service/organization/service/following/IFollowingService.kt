package o_o.service.organization.service.following

import o_o.service.organization.model.following.Following
import o_o.service.organization.base.service.IBaseCRUDService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.security.Principal

interface IFollowingService: IBaseCRUDService<Following> {
  fun followUnfollow(id: Long, principal: Principal): Boolean

  fun getFollowings(userId: Long?, principal: Principal): List<Following>
  fun getFollowings(userId: Long?, principal: Principal, pageable: Pageable): Page<Following>
  fun getFollowersByOrganization(organizationId: Long): List<Following>
  fun getFollowersByOrganization(organizationId: Long, pageable: Pageable): Page<Following>
}