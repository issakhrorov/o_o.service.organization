package o_o.service.organization.base.specification

import o_o.service.organization.base.model.BaseModel
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecificationArgs

data class BaseModelSpecification<T: BaseModel>(
  private val id: Long? = null,
  private val deleted: Boolean? = null,
  private val ids: List<Long>? = null
) : BaseSpecification<T>() {
  override fun applyTo(args: KSpecificationArgs<T>) {}

  override fun entityType(): Class<T> = BaseModel::class.java as Class<T>
}