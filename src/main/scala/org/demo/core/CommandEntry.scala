package org.demo.core

import cats.effect.{ExitCode, IO}
import enumeratum.{Enum, EnumEntry}
import monix.eval.Task
import org.backuity.clist.{Command, opt}

sealed abstract class CommandEntry(name: String, description: String)
  extends Command(name, description)
    with EnumEntry {
  var help = opt[Boolean](default = false, description = "display list of available commands")
}

object CommandEntry extends Enum[CommandEntry] {
  override val values = findValues

  case object Run extends CommandEntry("run", "execute this application"){

  }

  case object Cats extends CommandEntry("cats", "use cases of cats"){
      def execution: IO[_] = {
        IO.unit
      }
  }
}