package o_o.service.organization.repo.following

import o_o.service.organization.model.following.Following
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
interface FollowingRepo: KRepository<Following, Long> {
  fun findByUserIdAndProfileIdAndDeletedFalse(userId: Long, profileId: Long): Following?
  fun findByUserIdAndDeletedFalse(userId: Long): List<Following>
  fun findByUserIdAndDeletedFalse(userId: Long, pageable: Pageable): Page<Following>
  fun findByProfileIdAndDeletedFalse(profileId: Long): List<Following>
  fun findByProfileIdAndDeletedFalse(profileId: Long, pageable: Pageable): Page<Following>
}