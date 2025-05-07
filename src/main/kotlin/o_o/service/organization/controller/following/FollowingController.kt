package o_o.service.organization.controller.following

import jakarta.websocket.server.PathParam
import o_o.service.organization.service.following.IFollowingService
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/followings")
class FollowingController(
  val service: IFollowingService,
  val sqlClient: KSqlClient
) {
  @PostMapping("/{id}")
  fun followUnfollow(@PathVariable id: Long, principal: Principal) {
    service.followUnfollow(id, principal)
  }

  @GetMapping("/followers")
  fun getAlLFollowersByOrganizationPaged(
    @PathParam("organization_id") organization_id: Long,
    principal: Principal,
    pageable: Pageable
  ) {
    service.getFollowersByOrganization(organization_id, pageable)
  }

  @GetMapping("/")
  fun getAllFollowingsByUserPaged(
    @PathParam("user_id") user_id: Long?,
    principal: Principal,
    pageable: Pageable
  ) {
    val result = service.getFollowings(user_id, principal, pageable)
  }

  @GetMapping("/followers/count")
  fun getAlLFollowersByOrganizationCount(
    @PathParam("organization_id") organization_id: Long,
    principal: Principal
  ) = service.getFollowersByOrganization(organization_id).size

  @GetMapping("/count")
  fun getFollowingsCountByUser(
    @PathParam("user_id") user_id: Long?,
    principal: Principal,
  ) = service.getFollowings(user_id, principal).size

}