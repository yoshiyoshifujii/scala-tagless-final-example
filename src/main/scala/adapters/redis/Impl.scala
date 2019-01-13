package adapters.redis

import adapters.Impl.SpecialF
import cats.data._
import domain.{ Account, AccountId }
import repositories.AccountRepository

import scala.concurrent.{ ExecutionContext, Future }

object Impl {

  implicit def accountRepository(implicit ec: ExecutionContext): AccountRepository[SpecialF] =
    new AccountRepository[SpecialF] {
      override def store(account: Account): SpecialF[AccountId] =
        ReaderT {
          case (_, redis) =>
            Future(account.id)
        }
    }

}
