package org.demo.core


import akka.Done
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import cats.effect._
import cats.implicits._
import com.typesafe.scalalogging.StrictLogging
import org.backuity.clist._

object Main extends IOApp with StrictLogging {

  import org.demo.core.CommandEntry._

  override def run(args: List[String]): IO[ExitCode] =
    new Parser(args)
      .withProgramName("scala-demo")
      .withHelpCommand("--help")
      .withDescription("This is a basic demo of picking the right tools for the right job in scala ecosystem")
      .version("1.0")
      .withCommands(values: _*) match {
      case Some(Cats) =>
        Cats.execution.as(ExitCode.Success)
      case Some(Run) =>
        val resources = for {
          _ <- Resource.make{ IO(logger.info("starting")) } {_ => IO(logger.info("stopping"))}
          system <- Resource.make { IO(ActorSystem("demo-system")) } { s => IO(s.terminate()) }
          mat <- Resource.make {IO(ActorMaterializer()(system))} { m => IO(m.shutdown()) }
        } yield {
          (system, mat)
        }

        def execution(resource: (ActorSystem, ActorMaterializer)): IO[_] = {
          implicit val (system, mat) = resource

          val output = Source(List(1, 2, 3, 4, 5, 6))
            .via(Flow[Int].dropWhile(_ < 5))
            .toMat(Sink.foreach(s => logger.info(s.toString)))(Keep.right)
            .run()

          IO(logger.info("Application started")) *> IO.fromFuture(IO(output)) *> IO.shift
        }

        resources.use(execution).guarantee(IO(logger.info("Application Ended"))).as(ExitCode.Success)
        
      case None =>
        IO.unit.as(ExitCode.Error)
    }

}
