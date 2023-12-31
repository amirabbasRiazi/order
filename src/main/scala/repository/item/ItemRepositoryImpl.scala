package repository.item
import domain.Item
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.H2Profile.api._

import scala.concurrent.Future

class ItemRepositoryImpl(db :Database) extends ItemRepository {

  override def findById(id: Long): Future[Option[Item]] = db.run {
    Item.itemTable.filter(_.id === id).result.headOption
  }
}
