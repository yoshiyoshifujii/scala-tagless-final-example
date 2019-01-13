package adapters.db

import adapters.Impl.SpecialF
import cats.data._
import domain.{ User, UserId }
import repositories.UserRepository

import scala.concurrent.{ ExecutionContext, Future }

object Impl {

  implicit def userRepository(implicit ec: ExecutionContext): UserRepository[SpecialF] =
    new UserRepository[SpecialF] {
      override def store(user: User): SpecialF[UserId] =
        ReaderT {
          case (db, _) =>
            Future(user.id)
        }
    }

}
