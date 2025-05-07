package o_o.service.organization.base.service

import o_o.service.organization.base.model.BaseModel

interface IBaseCRUDService<T: BaseModel> {

  fun create(entity: T, username: String?) : T
  fun createCollection(entities: List<T>, username: String?) : Collection<T>

  fun update(entity: T, username: String?) : T
  fun updateCollection(entities: List<T>, username: String?) : Collection<T>

  fun delete(entity: T, username: String?) : T
  fun deleteCollection(entities: List<T>, username: String?) : Collection<T>

  fun activate(entity: T, username: String?) : T

  fun deletePermanently(entity: T, username: String?) : Boolean
  fun deletePermanentlyCollection(entities: List<T>, username: String?) : Boolean

  fun getById(id: Long) : T
  fun getActiveById(id: Long) : T

//  fun getByIds(ids: List<Long>): Collection<T>

//  fun getAllWithDeleted() : Collection<T>
//  fun getAllWithDeleted(pageable: Pageable) : Page<T>

//  fun getAll(): Collection<T>
//  fun getAll(sort: Sort): Collection<T>
//  fun getAll(pageable: Pageable, sort: Sort? = null): Page<T>
}
