package myreversi.models

import myreversi.models.player.{Player, User}
import utest._

object UserTest extends TestSuite {


  def tests = TestSuite {
    'UserBlackはPlayer {
      assert(User.Black.isInstanceOf[Player])
    }
    'UserWhiteはPlayer {
      assert(User.White.isInstanceOf[Player])
    }
    'UserBlackはUser{
      assert(User.Black.isInstanceOf[User])
    }
    'UserWhiteはUser {
      assert(User.White.isInstanceOf[User])
    }
  }
}
