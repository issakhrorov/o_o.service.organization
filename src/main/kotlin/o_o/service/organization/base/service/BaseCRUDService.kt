package o_o.service.organization.base.service

import o_o.service.organization.base.model.BaseModel
import o_o.service.organization.exception.*
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
abstract class BaseCRUDService<T : BaseModel> {

  lateinit var _repository: KRepository<T, Long>
  lateinit var _className: String

  fun create(entity: T, username: String?): T {
    return try {
      prepareData(entity, getUsername(username))
      _repository.save(entity)
      entity
    } catch (ex: Exception) {
      throw LoggedError(CustomConflictException("Couldn't create entity: " + ex.message))
    }
  }

  fun createCollection(entities: List<T>, username: String?): Collection<T> {
    return try {
      for (item in entities) {
        prepareData(item, getUsername(username))
      }
      _repository.saveAll(entities)
      entities
    } catch (ex: Exception) {
      throw LoggedError(CustomConflictException("Couldn't create entities: " + ex.message))
    }
  }

  fun update(entity: T, username: String?): T {
    return try {
      prepareData(entity, getUsername(username))
      _repository.save(entity)
      entity
    } catch (ex: Exception) {
      throw LoggedError(CustomConflictException("Couldn't update entity: " + ex.message))
    }
  }

  fun updateCollection(entities: List<T>, username: String?): Collection<T> {
    return try {
      for (item in entities) {
        prepareData(item, getUsername(username))
      }
      _repository.saveAll(entities)
      entities
    } catch (ex: Exception) {
      throw LoggedError(CustomConflictException("Couldn't update entity: " + ex.message))
    }
  }

  fun delete(entity: T, username: String?): T {
//    entity.deleted = true
    return try {
      update(entity, username)
      entity
    } catch (ex: Exception) {
      throw LoggedError(CustomConflictException("Couldn't delete entity: " + ex.message))
    }
  }

  fun activate(entity: T, username: String?): T {
//    entity.deleted = false
    return try {
      update(entity, username)
      entity
    } catch (ex: Exception) {
      throw LoggedError(CustomConflictException("Couldn't activate entity: " + ex.message))
    }
  }

  fun deleteCollection(entities: List<T>, username: String?): Collection<T> {
    return try {
      for (item in entities) {
//        item.deleted = true
      }
      updateCollection(entities, username)
      entities
    } catch (ex: Exception) {
      throw LoggedError(CustomConflictException("Couldn't delete entities: " + ex.message))
    }
  }

  //uncomment when it is needed
  fun deletePermanently(entity: T, username: String?): Boolean {
    return try {
      _repository.deleteById(entity.id)
      true
    } catch (ex: Exception) {
      false
    }
  }

  fun deletePermanentlyCollection(entities: List<T>, username: String?): Boolean {
    return try {
      _repository.deleteAll(entities)
      println("Delete permanently collection by $username")
      true
    } catch (ex: Exception) {
      false
    }
  }

  fun getById(id: Long): T {
    val entity = _repository.findById(id)
    if (entity.isPresent)
      return entity.get()
    else
      throw LoggedError(CustomEntryNotFoundException("$_className not found with ID #$id"))
  }

  fun getActiveById(id: Long): T {
    val entity = getById(id)
    return if (!entity.deleted)
      entity
    else
      throw LoggedError(CustomEntryNotFoundException("Active $_className with ID #$id not found"))
  }

//  fun getAllWithDeleted(): Collection<T> {
//    val specification: KSpecification<T> = BaseModelSpecification(null, null)
//    return _repository.findAll()
//  }
//
//  fun getAllWithDeleted(pageable: Pageable): Page<T> {
//    val specification: KSpecification<T> = BaseModelSpecification(null, null)
//    return _repository.findAll(specification, pageable)
//  }

//  fun getAll(): Collection<T> {
//    val specification: KSpecification<T> = BaseModelSpecification(null, false)
//    return _repository.findAll(specification)
//  }
//
//
//  fun getByIds(ids: List<Long>): Collection<T> {
//    val specification: KSpecification<T> = BaseModelSpecification(null, false, null, ids)
//    return _repository.findAll(specification)
//  }
//
//
//  fun getAll(sort: Sort): Collection<T> {
//    val specification: KSpecification<T> = BaseModelSpecification(null, false)
//    return _repository.findAll(specification, sort)
//  }
//
//  fun getAll(pageable: Pageable, sort: Sort? = null): Page<T> {
//    val modifiedPageable = if (sort == null)
//      pageable
//    else
//      PageRequest.of(pageable.pageNumber, pageable.pageSize, sort)
//
//    val specification: KSpecification<T> = BaseModelSpecification(null, false)
//    return _repository.findAll(specification, modifiedPageable)
//  }


  fun prepareData(baseModel: BaseModel, username: String): BaseModel {
    val now = Date()
    if (baseModel.id == 0L) {
//      baseModel.createdBy = username
//      baseModel.created = now
//      baseModel.deleted = false
    }

//    baseModel.modifiedBy = username
//    baseModel.modified = now
    return baseModel
  }

  private fun getUsername(username: String?): String {
    return username ?: "undefined"
  }

}
