package repository.item

import domain.Item

import scala.concurrent.Future

trait ItemRepository {

  def findById(id: Long): Future[Option[Item]]

}
