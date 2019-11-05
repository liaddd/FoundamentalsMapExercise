import java.math.BigDecimal
import java.time.LocalDateTime

data class Order(val orderId: Int, val creationDate: LocalDateTime, val orderLines: List<OrderLine>)
data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

class OrdersAnalyzer {

    fun totalDailySales(orders: List<Order>): Map<DaysOfWeek, Int> {
        val map = hashMapOf<DaysOfWeek, Int>()

        for (order in orders) {
            var sum = 0
            val key: DaysOfWeek? = DaysOfWeek.values().find { it.day == order.creationDate.dayOfWeek.value }
            for (orderLine in order.orderLines) {
                sum += orderLine.quantity
            }
            map[key!!] = if (map.containsKey(key)) map.getValue(key).plus(sum) else sum
        }

        return map
    }

}

fun main() {

    val sales = OrdersAnalyzer().totalDailySales(getOrders())
    print(sales)

}

fun getOrders(): ArrayList<Order> {
    val orderLines1: List<OrderLine> = listOf(
        OrderLine(9872, "Pencil", 3, BigDecimal(3.00))
    )

    val orderLines2: List<OrderLine> = listOf(
        OrderLine(9872, "Pencil", 2, BigDecimal(3.00)),
        OrderLine(1746, "Eraser", 1, BigDecimal(1.00))
    )

    val orderLines3: List<OrderLine> = listOf(
        OrderLine(5723, "Pen", 4, BigDecimal(4.22)),
        OrderLine(9872, "Pencil", 3, BigDecimal(3.12)),
        OrderLine(3433, "Erasers Set", 1, BigDecimal(6.15))
    )

    val orderLines4: List<OrderLine> = listOf(
        OrderLine(5723, "Pen", 7, BigDecimal(4.22)),
        OrderLine(3433, "Erasers Set", 2, BigDecimal(6.15))
    )

    val orderLines5: List<OrderLine> = listOf(
        OrderLine(9872, "Pencil", 4, BigDecimal(3.12)),
        OrderLine(4098, "Marker", 5, BigDecimal(4.50))
    )


    return arrayListOf(
        Order(554, LocalDateTime.parse("2017-03-25T10:35:20"), orderLines1),
        Order(555, LocalDateTime.parse("2017-03-25T10:35:20"), orderLines2),
        Order(453, LocalDateTime.parse("2017-03-27T14:53:12"), orderLines3),
        Order(431, LocalDateTime.parse("2017-03-20T14:53:12"), orderLines4),
        Order(690, LocalDateTime.parse("2017-03-26T12:15:02"), orderLines5)
    )
}


enum class DaysOfWeek(val day: Int) {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7)
}