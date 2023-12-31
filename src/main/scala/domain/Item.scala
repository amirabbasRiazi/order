package domain

import domain.Order.orderTable
import domain.Product.productTable
import slick.lifted.{ProvenShape, Tag}
import slick.jdbc.PostgresProfile.api._

import java.sql.Timestamp

case class Item(
               id: Long,
               registeredDate: Timestamp,
               updatedAt: Timestamp,
               cost: Int,
               taxAmount: Int,
               shoppingFee: Int,
               discount: Option[Int],
               productId: Long,
               orderId: Long,
               )

object Item {

  private val tableName = "items"

  class Items(tag: Tag) extends Table[Item](tag, tableName) {

    def id = column[Long]("id", O.PrimaryKey)

    def registeredDate = column[Timestamp]("registered_date")

    def updatedAt = column[Timestamp]("updated_at")

    def cost = column[Int]("cost")

    def taxAmount = column[Int]("tax_amount")

    def shoppingFee = column[Int]("shopping_fee")

    def discount = column[Option[Int]]("discount")

    def orderId = column[Long]("order_id")

    def productId = column[Long]("product_id")

    def product = foreignKey("product_fk", productId, productTable)(_.id)

    def order = foreignKey("order_fk", orderId, orderTable)(_.id)


    override def * : ProvenShape[Item] = (
      id,
      registeredDate,
      updatedAt,
      cost,
      taxAmount,
      shoppingFee,
      discount,
      orderId,
      productId
    ) <> ((Item.apply _).tupled, Item.unapply)
  }

  val itemTable = TableQuery[Items]
}