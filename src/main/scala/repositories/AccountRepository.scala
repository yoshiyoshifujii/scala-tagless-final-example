package repositories

import domain.{ Account, AccountId }

import scala.language.higherKinds

trait AccountRepository[F[_]] {
  def store(account: Account): F[AccountId]
}
