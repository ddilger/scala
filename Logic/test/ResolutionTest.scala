package test

import logiclibrary.Clause

import logiclibrary.Formula

import logiclibrary.Resolution

import logiclibrary.Literal

/**
 * @author Daniel
 */
object ResolutionTest {
  //Note: I need tests for the definition of Formula equality
  //Expected: true
    def test1() : Unit = {
    
    val A = new Literal("A")
    val B = new Literal("B")
    val C = new Literal("C")
    val D = new Literal("D")
    val not_D = new Literal(false, "D")
    val E = new Literal("E")
    val not_E = new Literal(false, "E")
    val clause_1 = new Clause(Set(A, B, D.negation(), C.negation()))
    val clause_2 = new Clause(Set(B, D, A.negation(), C))
    
    println(Resolution.all_resolvents(clause_1, clause_2))
   
  }
  //Expected: false
  def test2() : Unit = {
    
    val A = new Literal("A")
    val B = new Literal("B")
    val C = new Literal("C")
    val D = new Literal("D")
    val clause_1 = new Clause(Set(D.negation(), C.negation()))
    val clause_2 = new Clause(Set(D, C))
    
    val f1 = new Formula(Set(clause_1, clause_2))
    println(Resolution.satisfiable(f1))
   
  }
  
  //Expected: true
  def test3() : Unit = {
    val A = new Literal("A")
    val B = new Literal("B")
    
    val clause_1 = new Clause(Set(A, B)) 
    val clause_2 = new Clause(Set(A))

    val f1 = new Formula(Set(clause_1, clause_2))
    println(Resolution.satisfiable(f1))
  }
  
  //Expected: true
  def test4() : Unit = {
    val A = new Literal("A")
    val B = new Literal("B")
    val C = new Literal("C")
    val D = new Literal("D")
    val clause_1 = new Clause(Set(A, B, D.negation(), C.negation()))
    val clause_2 = new Clause(Set(A, B)) 
    val f1 = new Formula(Set(clause_1, clause_2))
    println(Resolution.satisfiable(f1))
  }
  
  //Expected: true
    def test5() : Unit = {
    val A = new Literal("A")
    val clause_1 = new Clause(Set(A))
    val clause_2 = new Clause(Set(A))

    val f1 = new Formula(Set(clause_1, clause_2))
   // println(Resolution.res(f1))
    println(Resolution.satisfiable(f1))
  }
  //Expected: false
  def test6() : Unit = {
    val A = new Literal("A")
    val not_A = new Literal(false, "A")
    val clause_1 = new Clause(Set(A))
    val clause_2 = new Clause(Set(not_A))

    val f1 = new Formula(Set(clause_1, clause_2))
   // println(Resolution.res(f1))
    println(Resolution.satisfiable(f1))
  }
  
  //Expected: false
  def test7() : Unit = {
    val A = new Literal("A")
    val B = new Literal("B")
    val not_A = new Literal(false, "A")
    val clause_1 = new Clause(Set(A, B))
    val clause_2 = new Clause(Set(not_A))

    val f1 = new Formula(Set(clause_1, clause_2))
   // println(Resolution.res(f1))
    println(Resolution.satisfiable(f1))
  }
}