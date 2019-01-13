import adapters.Impl.SpecialF
import adapters.db.DBConnection
import adapters.redis.RedisConnection
import org.scalatest.FreeSpec
import usercases.AccountCreateUseCase

import scala.concurrent.duration._
import scala.concurrent.{ Await, ExecutionContext }

class ControllerSpec extends FreeSpec {

  "sample" in {

    import adapters.db.Impl._
    import adapters.redis.Impl._
    import cats.instances.all._

    implicit val ec: ExecutionContext = ExecutionContext.global

    val useCase = new AccountCreateUseCase[SpecialF]
    val result  = useCase.execute("name", "email@email.com").run((new DBConnection, new RedisConnection))
    assert(Await.result(result, 1.second) === "UserId(1)-AccountId(2)")

  }

}
