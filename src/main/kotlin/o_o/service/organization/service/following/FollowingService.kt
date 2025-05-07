package o_o.service.organization.service.following

import o_o.service.organization.base.service.BaseCRUDService
import o_o.service.organization.model.following.Following
import o_o.service.organization.repo.following.FollowingRepo
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class FollowingService(
  val mainRepo: FollowingRepo,
  val sqlClient: KSqlClient
): IFollowingService, BaseCRUDService<Following>() {
  init {
    this._repository = mainRepo
    this._className = Following::class.java.simpleName
  }

  override fun followUnfollow(id: Long, principal: Principal): Boolean {
    val userId = principal.name.toLong()
    val entity = mainRepo.findByUserIdAndProfileIdAndDeletedFalse(userId, id)

    entity?.let { delete(entity, principal.name) }
      ?:
        sqlClient.save(
          Following {
            this.userId = userId
            profileId = id
          }
        )

    return true
  }

  override fun getFollowings(userId: Long?, principal: Principal): List<Following> {
    val user = userId ?: principal.name.toLong()
    return mainRepo.findByUserIdAndDeletedFalse(user)
  }

  override fun getFollowings(userId: Long?, principal: Principal, pageable: Pageable): Page<Following> {
    val user = userId ?: principal.name.toLong()
    return mainRepo.findByUserIdAndDeletedFalse(user, pageable)
  }

  override fun getFollowersByOrganization(organizationId: Long) =
    mainRepo.findByProfileIdAndDeletedFalse(organizationId)

  override fun getFollowersByOrganization(organizationId: Long, pageable: Pageable) =
    mainRepo.findByProfileIdAndDeletedFalse(organizationId, pageable)
}