package repositories

import domain.{ User, UserId }

import scala.language.higherKinds

trait UserRepository[F[_]] {
  def store(user: User): F[UserId]
}
