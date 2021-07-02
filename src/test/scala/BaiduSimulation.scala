import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
class BaiduSimulation extends Simulation {
  //设置请求的根路径
  val httpConf = http.baseUrl("http://localhost:8888/retryLimit")
  /*0
    运行100秒 during 默认单位秒,如果要用微秒 during(100 millisecond)
   */
//  val scn = scenario("BaiduSimulation").during(10){
//    exec(http("retryLimit").get("/")).check(status.is(200))
//
//  }
  val scn = scenario("BaiduSimulation").during(120){
  exec(http("retryLimit")
    .get("/")
    .check(status.is(200))
  )
}
  //设置线程数
  //  setUp(scn.inject(rampUsers(500) over(10 seconds)).protocols(httpConf))
  setUp(scn.inject(atOnceUsers(1)).protocols(httpConf))
}
