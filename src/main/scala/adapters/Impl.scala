package adapters

import adapters.db.DBConnection
import adapters.redis.RedisConnection
import cats.data.ReaderT

import scala.concurrent.Future

object Impl {
  type SpecialF[A] = ReaderT[Future, (DBConnection, RedisConnection), A]
}
