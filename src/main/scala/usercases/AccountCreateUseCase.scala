package usercases

import cats._
import cats.implicits._
import domain.{ Account, AccountId, User, UserId }
import repositories.{ AccountRepository, UserRepository }

import scala.language.higherKinds

class AccountCreateUseCase[F[_]: Monad: UserRepository: AccountRepository] {

  def execute(name: String, email: String): F[String] = {
    for {
      userId <- UserRepository[F].store(
        User(UserId("1"), name)
      )
      accountId <- AccountRepository[F].store(
        Account(AccountId("2"), userId, email)
      )
    } yield s"$userId-$accountId"
  }

}
