package domain

object OrderState extends Enumeration {
    type OrderState = Value
    val Pending, Confirmed, Shipped, Delivered, Cancelled = Value
}
