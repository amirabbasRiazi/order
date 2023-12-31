package model

import java.sql.Timestamp
import domain.{Item, Product}

case class ItemDTO(
                    id: Long,
                    registeredDate: Timestamp,
                    updatedAt: Timestamp,
                    cost: Int,
                    taxAmount: Int,
                    shoppingFee: Int,
                    discount: Option[Int],
                    product: Product,
                  )

object ItemDTO {

  def from(item: Item, product: Product) = {
    ItemDTO(id = item.id, registeredDate = item.registeredDate, updatedAt = item.updatedAt, cost = item.cost,
      taxAmount = item.taxAmount, shoppingFee = item.shoppingFee, discount = item.discount, product = product)
  }
}