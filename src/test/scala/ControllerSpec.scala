import cats.data.ReaderT
import domain.{ Account, AccountId, User, UserId }
import org.scalatest.FreeSpec
import repositories.{ AccountRepository, UserRepository }
import usercases.AccountCreateUseCase

import scala.concurrent.duration._
import scala.concurrent.{ Await, ExecutionContext, Future }

class ControllerSpec extends FreeSpec {

  "sample" in {

    class DBConnection
    class RedisConnection

    type SpecialF[A] = ReaderT[Future, (DBConnection, RedisConnection), A]

    implicit def userRepository(implicit ec: ExecutionContext): UserRepository[SpecialF] =
      new UserRepository[SpecialF] {
        override def store(user: User): SpecialF[UserId] =
          ReaderT {
            case (db, _) =>
              Future(user.id)
          }
      }

    implicit def accountRepository(implicit ec: ExecutionContext): AccountRepository[SpecialF] =
      new AccountRepository[SpecialF] {
        override def store(account: Account): SpecialF[AccountId] =
          ReaderT {
            case (_, redis) =>
              Future(account.id)
          }
      }

    import cats.instances.all._

    implicit val ec: ExecutionContext = ExecutionContext.global

    val useCase = new AccountCreateUseCase[SpecialF]
    val result  = useCase.execute("name", "email@email.com").run((new DBConnection, new RedisConnection))
    assert(Await.result(result, 1.second) === "UserId(1)-AccountId(2)")

  }

}
