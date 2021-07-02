import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://localhost:8888/multiLimit")
		.inferHtmlResources()
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("zh-Hans-CN;q=1")
		.userAgentHeader("(null)/(null) (Mac OS X Version 11.2 (Build 20D64))")

	val headers_0 = Map(
		"Accept" -> "*/*",
		"Cache-Control" -> "no-cache",
		"Content-Type" -> "application/octet-stream",
		"Proxy-Connection" -> "keep-alive")

	val headers_2 = Map(
		"Accept-Language" -> "zh-CN,zh;q=0.9,zh-TW;q=0.8,en-US;q=0.7,en;q=0.6",
		"Cache-Control" -> "no-cache",
		"Pragma" -> "no-cache",
		"Proxy-Connection" -> "keep-alive",
		"User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")

    val uri2 = "http://www.gstatic.com/generate_204"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.post("/q")
			.headers(headers_0)
			.body(RawFileBody("com/zhw/recordedsimulation/0000_request.bin"))
			.resources(http("request_1")
			.post("/q")
			.headers(headers_0)
			.body(RawFileBody("com/zhw/recordedsimulation/0001_request.bin")))
			.check(status.is(404)))
		.pause(46)
		.exec(http("request_2")
			.get(uri2)
			.headers(headers_2))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}