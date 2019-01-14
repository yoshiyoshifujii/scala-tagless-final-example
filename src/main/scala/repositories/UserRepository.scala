package repositories

import domain.{ User, UserId }

import scala.language.higherKinds

trait UserRepository[F[_]] {
  def store(user: User): F[UserId]
}

object UserRepository {
  def apply[F[_]](implicit F: UserRepository[F]): UserRepository[F] = F
}
