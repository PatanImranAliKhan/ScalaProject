import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import akka.stream.ActorMaterializer
import scala.language.postfixOps
import scala.io.StdIn._

import java.net.URLEncoder
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
object Demo {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  import system.dispatcher
//      val source =
//        """
//          |object SimpleApp {
//          |  val aField = 2
//          |
//          |  def aMethod(x: Int) = x + 1
//          |
//          |  def main(args: Array[String]) = {
//          |    println(aMethod(aField))
//          |  }
//          |}
//    """.stripMargin
//  val request = HttpRequest(
//    method = HttpMethods.POST,
//    uri = "http://markup.su/api/highlighter",
//    entity = HttpEntity(
//      ContentTypes.`application/x-www-form-urlencoded`,
//
//      s"source=${URLEncoder.encode(source.trim, "UTF-8")}&language=Scala&theme=Sunburst"
//    )
//  )
//  def fethcingRequest() = {
//    val responseFuture = Http().singleRequest(request)
//    responseFuture.flatMap(_.entity.toStrict(2 seconds)).map(_.data.utf8String).foreach(println)
//  }


  def GetResponce(myCode: String,url: String): Future[String] = {
    val responseFuture = Http().singleRequest(
      HttpRequest(
        method = HttpMethods.GET,
        uri = url,
        entity = HttpEntity(
          ContentTypes.`application/x-www-form-urlencoded`,
          s"source=${URLEncoder.encode(myCode.trim, "UTF-8")}&language=Scala&theme=Sunburst"
        )
      )
    )
    responseFuture
      .flatMap(_.entity.toStrict(2 seconds))
      .map(_.data.utf8String)
  }
//  https://jsonplaceholder.typicode.com/todos
//  https://jsonplaceholder.typicode.com/posts
//  https://api.github.com/users/hadley/orgs
//  https://api.github.com/users/hadley/repos

  def main(args: Array[String]): Unit =
  {
    print("Please Enter the url : ")
    var url=readLine();
    println()
    GetResponce("demo",url).foreach(println)
  }

}
