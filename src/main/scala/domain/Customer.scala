package domain


import slick.lifted.{TableQuery, Tag}
import slick.jdbc.PostgresProfile.api._

import java.sql.Timestamp

case class Customer(
                     id: Long,
                     registeredDate: Timestamp,
                     updatedAt: Timestamp,
                     name: String,
                     lastName: String,
                     mobile: String,
                   )


object Customer {

  private val tableName = "orders"

  class Customers(tag: Tag) extends Table[Customer](tag, tableName) {

    def id = column[Long]("id", O.PrimaryKey)

    def registeredDate = column[Timestamp]("registered_date")

    def updatedAt = column[Timestamp]("updated_at")

    def name = column[String]("name")

    def lastName = column[String]("lastname")

    def mobile = column[String]("mobile")


    override def * = (id, registeredDate, updatedAt, name, lastName, mobile) <> ((Customer.apply _).tupled, Customer.unapply)
  }

  val customerTable = TableQuery[Customers]
}


