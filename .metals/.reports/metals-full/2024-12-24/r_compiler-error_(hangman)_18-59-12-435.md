file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala
### scala.reflect.internal.FatalError: 
  ThisType(variable block) for sym which is not a class
     while compiling: file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.13.12
    compiler version: version 2.13.12
  reconstructed args: -release:11 -classpath <WORKSPACE>\.bloop\hangman\bloop-bsp-clients-classes\classes-Metals-O7BQzod-Rpy4y1c-ITNmSw==;<HOME>\AppData\Local\bloop\cache\semanticdb\com.sourcegraph.semanticdb-javac.0.10.3\semanticdb-javac-0.10.3.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\modules\scala-parser-combinators_2.13\2.3.0\scala-parser-combinators_2.13-2.3.0.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: Ident(_CURSOR_c)
       tree position: line 8 of file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala
            tree tpe: <error>
              symbol: value <error> in class <error>
   symbol definition: val <error>: <error> (a TermSymbol)
      symbol package: <none>
       symbol owners: value <error> -> class <error>
           call site: <none> in <none>

== Source file context for tree position ==

     5     val c = 4
     6     c + 2
     7   }
     8   println(_CURSOR_c)
     9 }
    10 

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.13.12
Classpath:
<WORKSPACE>\.bloop\hangman\bloop-bsp-clients-classes\classes-Metals-O7BQzod-Rpy4y1c-ITNmSw== [exists ], <HOME>\AppData\Local\bloop\cache\semanticdb\com.sourcegraph.semanticdb-javac.0.10.3\semanticdb-javac-0.10.3.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar [exists ], <HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\modules\scala-parser-combinators_2.13\2.3.0\scala-parser-combinators_2.13-2.3.0.jar [exists ]
Options:
-Yrangepos -Xplugin-require:semanticdb -release 11


action parameters:
offset: 129
uri: file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala
text:
```scala
object Main extends App {
  val x: Int = 1 + 1
  var y: Int = x + 3
  var block = {
    val c = 4
    c + 2
  }
  println(@@c)
}
```



#### Error stacktrace:

```
scala.reflect.internal.Reporting.abort(Reporting.scala:70)
	scala.reflect.internal.Reporting.abort$(Reporting.scala:66)
	scala.reflect.internal.SymbolTable.abort(SymbolTable.scala:28)
	scala.reflect.internal.Types$ThisType.<init>(Types.scala:1394)
	scala.reflect.internal.Types$UniqueThisType.<init>(Types.scala:1414)
	scala.reflect.internal.Types$ThisType$.apply(Types.scala:1418)
	scala.meta.internal.pc.AutoImportsProvider$$anonfun$autoImports$3.applyOrElse(AutoImportsProvider.scala:75)
	scala.meta.internal.pc.AutoImportsProvider$$anonfun$autoImports$3.applyOrElse(AutoImportsProvider.scala:60)
	scala.collection.immutable.List.collect(List.scala:267)
	scala.meta.internal.pc.AutoImportsProvider.autoImports(AutoImportsProvider.scala:60)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$autoImports$1(ScalaPresentationCompiler.scala:384)
```
#### Short summary: 

scala.reflect.internal.FatalError: 
  ThisType(variable block) for sym which is not a class
     while compiling: file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala
        during phase: globalPhase=<no phase>, enteringPhase=parser
     library version: version 2.13.12
    compiler version: version 2.13.12
  reconstructed args: -release:11 -classpath <WORKSPACE>\.bloop\hangman\bloop-bsp-clients-classes\classes-Metals-O7BQzod-Rpy4y1c-ITNmSw==;<HOME>\AppData\Local\bloop\cache\semanticdb\com.sourcegraph.semanticdb-javac.0.10.3\semanticdb-javac-0.10.3.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\scala-library\2.13.12\scala-library-2.13.12.jar;<HOME>\AppData\Local\Coursier\cache\v1\https\repo1.maven.org\maven2\org\scala-lang\modules\scala-parser-combinators_2.13\2.3.0\scala-parser-combinators_2.13-2.3.0.jar -Xplugin-require:semanticdb -Yrangepos -Ymacro-expand:discard -Ycache-plugin-class-loader:last-modified -Ypresentation-any-thread

  last tree to typer: Ident(_CURSOR_c)
       tree position: line 8 of file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala
            tree tpe: <error>
              symbol: value <error> in class <error>
   symbol definition: val <error>: <error> (a TermSymbol)
      symbol package: <none>
       symbol owners: value <error> -> class <error>
           call site: <none> in <none>

== Source file context for tree position ==

     5     val c = 4
     6     c + 2
     7   }
     8   println(_CURSOR_c)
     9 }
    10 