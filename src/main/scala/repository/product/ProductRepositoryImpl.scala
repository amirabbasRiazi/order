package repository.product
import domain.Product
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.H2Profile.api._

import java.sql.Timestamp
import scala.concurrent.Future

class ProductRepositoryImpl(db: Database) extends ProductRepository  {

  override def findById(id: Long): Future[Option[Product]] = db.run {
    Product.productTable
      .filter(_.id === id)
      .result
      .headOption
  }

  override def findByRegisteredDate(date: Timestamp): Future[Seq[Product]] = ???
}
