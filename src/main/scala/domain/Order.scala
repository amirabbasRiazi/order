package domain

import slick.lifted.{TableQuery, Tag}
import slick.jdbc.PostgresProfile.api._

import java.sql.Timestamp


case class Order(
                  id: Long,
                  registeredDate: Timestamp,
                  updatedAt: Timestamp,
                  title: String,
                  state: String,
                  customerId: Long // Should add some field like payment_method, description,...
                )

object Order {

  private val tableName = "orders"

  class Orders(tag: Tag) extends Table[Order](tag, tableName) {

    def id = column[Long]("id", O.PrimaryKey)

    def registeredDate = column[Timestamp]("registered_date")

    def updatedAt = column[Timestamp]("updated_at")

    def title = column[String]("title")

    def state = column[String]("state")

    def customerId = column[Long]("customer_id")

    def customer = foreignKey("customer_fk", customerId, Customer.customerTable)(_.id)


    override def * = (id, registeredDate, updatedAt, title, state, customerId) <> ((Order.apply _).tupled, Order.unapply)
  }

  val orderTable = TableQuery[Orders]
}