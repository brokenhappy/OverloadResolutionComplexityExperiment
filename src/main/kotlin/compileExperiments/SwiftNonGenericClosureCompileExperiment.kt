package compileExperiments

import Environment
import org.intellij.lang.annotations.Language

class SwiftNonGenericClosureCompileExperiment(environment: Environment) : SwiftCompileExperiment(environment) {
    override val description: String get() = "Swift nested closures with generic ++ overloads"

    @Language("swift")
    override fun generateCode(n: Int): String = """
        infix operator ++: AdditionPrecedence
        
        struct A {}
        struct B {}

        func ++ <A>(lhs: A, rhs: A) -> A {lhs}
        func ++ <B>(lhs: B, rhs: B) -> B {rhs}
        
        func foo(_:(B) -> A) -> A {A()}
        func foo(_:(B) -> B) -> B {B()}
        
        func ab() -> A {A()}
        func ab() -> B {B()}
        
        let _ = foo { $0 ${" ++ ab()".repeat(n)} }
    """.trimIndent()
//        let _ = foo { x in ${(0..n).fold("x") { acc, _ -> "foo { _ in $acc }" } } }
//        let _ = ${(0 until n).joinToString(" + ") { listOf("-1", "+1", "-(1)", "+(1)", "1").random() }}
}
