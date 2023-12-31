package repository.product

import java.sql.Timestamp
import scala.concurrent.Future


trait ProductRepository {

  def findById(id: Long): Future[Product]

  def findByRegisteredDate(date: Timestamp): Future[Seq[Product]]

}