package service

import model.OrderDTO
import domain.Product
import repository.order.OrderRepository

import java.sql.Timestamp
import java.time.temporal.ChronoUnit
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext}

class OrderService(repository: OrderRepository)(implicit ec: ExecutionContext) {


  def getOrderCount(dateFrom: Timestamp, dateTo: Timestamp): Map[String, Int] = {
    Await.result(for {
      orders <- repository.findByInterval(dateFrom, dateTo)
      result = orders.groupBy(_._1._1).map { case ((order, pairs)) =>
        val (items, products) = pairs.unzip
        OrderDTO.from(order, items.map(_._2), products.head)
      }
    } yield groupOrdersByProductAge(result.toSeq), 10.seconds)
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
