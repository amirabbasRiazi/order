package repository

import slick.jdbc.JdbcBackend.Database

object DatabaseProvider {
  val db = Database.forConfig("postgres")
}
