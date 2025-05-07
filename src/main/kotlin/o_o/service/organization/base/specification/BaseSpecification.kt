package o_o.service.organization.base.specification

import o_o.service.organization.base.model.BaseModel
import org.babyfish.jimmer.sql.kt.ast.query.specification.KSpecification

abstract class BaseSpecification<T: BaseModel> : KSpecification<T>