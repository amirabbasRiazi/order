package model

import java.sql.Timestamp

case class ProductDTO(
                       id: Long,
                       registeredDate: Timestamp,
                       updatedAt: Timestamp,
                       itemId: Long,
                       name: String,
                       description: Option[String],
                       productType: String, //todo should add enum for this
                       price: Int,
                       quantity: Option[Int],
                       weight: Option[Int],
                       height: Option[Int]
                     )
