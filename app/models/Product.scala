package models

import java.text.SimpleDateFormat
import java.util.Date

/**
 * An entry in the product catalogue.
 *
 * @param ean EAN-13 code - a unique product identifier
 * @param name Product name
 * @param description Product description
 */
case class Product(ean: Long, name: String, description: String) {
  override def toString = "%s - %s".format(ean, name)
}

/**
 * Products data access
 */
object Product {
  
  var products = Set(
    Product(5010255079763L, "Paperclips Large", "Large Plain Pack of 1000"),
    Product(5018206244666L, "Giant Paperclips", "Giant Plain 51mm 100 pack"),
    Product(5018306332812L, "Paperclip Giant Plain", "Giant Plain Pack of 10000"),
    Product(5018306312913L, "No Tear Paper Clip", "No Tear Extra Large Pack of 1000"),
    Product(5018206244611L, "Zebra Paperclips", "Zebra Length 28mm Assorted 150 Pack")
  )

  /**
   * Products sorted by EAN code.
   */
  def findAll = {
    newProductsCounter = 0
    products.toList.sortBy(_.ean)
  }

  /**
   * The product with the given EAN code.
   */
  def findByEan(ean: Long): Option[Product] = products.find(_.ean == ean)

  /**
   * Products whose name matches the given query.
   */
  def findByName(query: String) = products.filter(_.name.contains(query))

  /**
   * Deletes a product from the catalog.
   */
  def remove(product: Product) = {
    val oldProducts = products
    products = products - product
    oldProducts.contains(product)
  }

  /**
   * Adds a product to the catalog.
   */
  def add(product: Product) {
    products = products + product
  }

  var newProductsCounter : Int = 0
}

/**
  * A request to prepare an order by ‘picking’ an order line (a
  * quantity of a particular product) from the given warehouse location.
  * A list of preparation requests is called a ‘pick list’.
  */
case class Preparation(orderNumber: Long, product: Product,
                       quantity: Int, location: String)

/**
  * Pick list data access object stub (test data).
  */
object PickList {

  def find(warehouse: String) : List[Preparation] = {
    val p = Product(5010255079763L, "Large paper clips 1000 pack","")
    List(
      Preparation(3141592, p, 200, "Aisle 42 bin 7"),
      Preparation(6535897, p, 500, "Aisle 42 bin 7"),
      Preparation(93, p, 100, "Aisle 42 bin 7")
    )
  }
}

/**
  * Warehouse data access object stub.
  */
object Warehouse {

  def find() = {
    List("W123", "W456")
  }
}

object Order {

  def backlog(warehouse: String): String = {
    Thread.sleep(5000L)
    new SimpleDateFormat("mmss").format(new Date())
  }
}