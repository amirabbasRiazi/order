package model

import domain.{Item, Order, Product}
import domain.Order.{Orders, orderTable}

import java.sql.Timestamp

case class OrderDTO(
                   id: Long,
                     registeredDate: Timestamp,
                     updatedAt: Timestamp,
                     title: String,
                     state: String,
                     items: Seq[ItemDTO]
                   )

object OrderDTO {
  def from(order: Order, items: Seq[Item], product:  Product): OrderDTO = {
    OrderDTO (
      id = order.id,
      order.registeredDate,
      order.updatedAt,
      order.title,
      order.state,
      items = items.map(res => ItemDTO.from(res, product))
    )
  }
}
