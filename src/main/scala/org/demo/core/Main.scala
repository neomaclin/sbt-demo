package org.demo.core

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import cats.effect._
import cats.implicits._
import com.typesafe.scalalogging.StrictLogging
import monix.eval.{Task, TaskApp}
import org.backuity.clist.Parser

object Main extends TaskApp with StrictLogging{

  import CommandEntry._

  override def run(args: List[String]): Task[ExitCode] =
    new Parser(args)
      .withProgramName("scala-demo")
      .withHelpCommand("--help")
      .withDescription("This is a basic demo of picking the right tools for the right job in scala ecosystem")
      .version("1.0")
      .withCommands(values: _*) match {
      case Some(Cats) => Cats.execution.as(ExitCode.Success)
      case Some(Run) =>
          val resources = for {
            _ <- Resource.make(Task(logger.info("starting")))(_ => Task(logger.info("stopping")))
            system <- Resource.make(Task(ActorSystem("demo-system")))(s => Task(s.terminate()))
          } yield system

          def execution(resource: ActorSystem): Task[_] = {
            implicit val system = resource

            val output = Source(List(1, 2, 3, 4, 5, 6))
              .via(Flow[Int].dropWhile(_ < 5))
              .toMat(Sink.foreach(in => logger.info(in.toString)))(Keep.right)
              .run()

            Task(logger.info("Application started")) *> Task.fromFuture(output)
          }

          resources.use(execution).guarantee(Task(logger.info("Application Ended"))).as(ExitCode.Success)

      case None => Task.unit.as(ExitCode.Error)
    }

}
