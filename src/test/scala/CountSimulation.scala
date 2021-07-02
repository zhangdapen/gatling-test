import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CountSimulation extends Simulation {
  //设置请求的根路径
  val httpConf = http.baseUrl("http://localhost:8888/multiLimit")
  /*0
    运行100秒 during 默认单位秒,如果要用微秒 during(100 millisecond)
   */
//  val scn = scenario("BaiduSimulation").during(10){
//    exec(http("retryLimit").get("/")).check(status.is(200))
//
//  }
  val scn = scenario("CountSimulation").during(600){
  exec(http("count")
    .get("/")
    .check(status.is(200))
    .check(status.is(304))
  )
}
  //设置线程数
  //  setUp(scn.inject(rampUsers(500) over(10 seconds)).protocols(httpConf))
  setUp(scn.inject(atOnceUsers(10)).protocols(httpConf))
}
