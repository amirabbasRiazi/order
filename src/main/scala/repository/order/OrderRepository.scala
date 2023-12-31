package repository.order

import domain.{Item, Order, Product}

import java.sql.Timestamp
import scala.concurrent.Future

trait OrderRepository {

  def findById(id: Long): Future[Option[Order]]

  def findByInterval(dateFrom: Timestamp, dateTo: Timestamp):Future[Seq[((Order, Item), Product)]]

}
