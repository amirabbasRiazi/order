package service

import model.OrderDTO
import repository.order.OrderRepository

import java.sql.Timestamp
import java.time.temporal.ChronoUnit
import scala.concurrent.{ExecutionContext, Future}

class OrderService(repository: OrderRepository)(implicit ec: ExecutionContext) {

  def getOrderCount(dateFrom: Timestamp, dateTo: Timestamp): Future[Map[String, Int]] = {
    repository.findByInterval(dateFrom, dateTo).map {
      orders =>
        orders.groupBy(_._1._1).map { case (order, pairs) =>
          val (items, products) = pairs.unzip
          OrderDTO.from(order, items.map(_._2), products.head)
        }.toSeq
    }.map(groupOrdersByProductAge)
  }

  def groupOrdersByProductAge(orders: Seq[OrderDTO]): Map[String, Int] = {
    orders.flatMap(order => order.items.map(item => (item.product, order)))
      .groupBy { case (product, order) =>
        categorizeSinceCreation(
          ChronoUnit.MONTHS.between(
            product.registeredDate.toLocalDateTime,
            order.registeredDate.toLocalDateTime
          ))
      }
      .view.mapValues(_.length)
  }.toMap

  private def categorizeSinceCreation(monthsSinceCreation: Long): String = {
    if (monthsSinceCreation <= 3) "1-3 months"
    else if (monthsSinceCreation <= 6) "4-6 months"
    else if (monthsSinceCreation <= 12) "7-12 months"
    else ">12 months"
  }
}
