

import repository.DatabaseProvider
import repository.order.OrderRepositoryImpl
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.concurrent.Executors
import scala.concurrent.{Await, ExecutionContext}
import java.time.Instant
import service.OrderService
import scala.concurrent.duration.DurationInt
import scala.util.Try

object Main {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(5))
  def main(args: Array[String]): Unit = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    val dateFrom = Try(
      Timestamp.from(dateFormat.parse(args(0)).toInstant))
      .toOption
      .getOrElse(Timestamp.from(dateFormat.parse("2022-05-10 14:20:00").toInstant))

    val dateTo = Try(
      Timestamp.from(dateFormat.parse(args(1)).toInstant))
      .toOption
      .getOrElse(Timestamp.from(Instant.now()))

    val orderService = new OrderService(new OrderRepositoryImpl(DatabaseProvider.db))

    println("Orders grouped by product age:")

    Await.result(
      orderService.getOrderCount(dateFrom, dateTo),
      10.seconds
    ).foreach {
      case (interval, ordersInInterval)  =>
      println(s"$interval months: ${ordersInInterval} orders")
    }

  }


}