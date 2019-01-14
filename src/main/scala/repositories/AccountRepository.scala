package repositories

import domain.{ Account, AccountId }

import scala.language.higherKinds

trait AccountRepository[F[_]] {
  def store(account: Account): F[AccountId]
}

object AccountRepository {
  def apply[F[_]](implicit F: AccountRepository[F]): AccountRepository[F] = F
}
