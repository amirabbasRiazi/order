package domain

import slick.driver.PostgresDriver
import slick.jdbc.PostgresProfile.api._

import java.sql.Timestamp


case class Product(
                    id: Long,
                    registeredDate: Timestamp,
                    updatedAt: Timestamp,
                    name: String,
                    description: Option[String],
                    productType: String, //todo should add enum for this
                    price: Int,
                    quantity: Option[Int],
                    weight: Option[Int],
                    height: Option[Int]
  )

object Product {

  private val tableName = "products"

  class Products(tag: Tag) extends Table[Product](tag, tableName) {
    def id = column[Long]("id", O.PrimaryKey) // This is the primary key column

    def registeredDate = column[Timestamp]("registered_date")

    def updatedAt = column[Timestamp]("updated_at")

    def name = column[String]("title")

    def description = column[Option[String]]("description")

    def productType = column[String]("product_type")

    def price = column[Int]("price")

    def quantity = column[Option[Int]]("quantity")

    def weight = column[Option[Int]]("weight")

    def height = column[Option[Int]]("height")


    def * = (
      id,
      registeredDate,
      updatedAt,
      name,
      description,
      productType,
      price,
      quantity,
      weight,
      height
    ) <> ((Product.apply _).tupled, Product.unapply)
  }

  val productTable = TableQuery[Products]

}


