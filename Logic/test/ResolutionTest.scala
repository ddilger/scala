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
    val clause_1 = new Clause(Set(A))
    val f1 = new Formula(Set(clause_1))
    println(Resolution.satisfiable(f1))
  }
   //Expected: false
  def test2() : Unit = {
    val A = new Literal("A")
    val not_A = A.negation()
    val clause_1 = new Clause(Set(A))
    val clause_2 = new Clause(Set(not_A))
    val f1 = new Formula(Set(clause_1, clause_2))
    println(Resolution.satisfiable(f1))    
  }
   //Expected: true
  def test3() : Unit = {
    val A = new Literal("A")
    val B = new Literal("B")
    val clause_1 = new Clause(Set(A))
    val clause_2 = new Clause(Set(B))
    val f1 = new Formula(Set(clause_1, clause_2))
    println(Resolution.satisfiable(f1))  
  }
   //Expected: false
  def test4() : Unit = {
    val A = new Literal("A")
    val not_A = A.negation()
    val clause_1 = new Clause(Set(A, not_A))
    val f1 = new Formula(Set(clause_1))
    println(Resolution.satisfiable(f1))      
  }
   //Expected: false
  def test5() : Unit = {
    val A = new Literal("A")
    val not_A = A.negation()
    val clause_1 = new Clause(Set(A))
    val clause_2 = new Clause(Set(not_A))
    val clause_3 = new Clause(Set(A, not_A))
    val f1 = new Formula(Set(clause_1, clause_2, clause_3))
    println(Resolution.satisfiable(f1)) 
  }
   //Expected: false
  def test6() : Unit = {
    val A = new Literal("A")
    val B = new Literal("B")
    val not_B = B.negation()
    val clause_1 = new Clause(Set(A))
    val clause_2 = new Clause(Set(B))
    val clause_3 = new Clause(Set(not_B))
    val f1 = new Formula(Set(clause_1, clause_2, clause_3))
    println(Resolution.satisfiable(f1))     
  }
   //Expected: true
  def test7() : Unit = {
    val A = new Literal("A")
    val B = new Literal("B")
    val not_B = B.negation()
    val clause_1 = new Clause(Set(A, not_B))
    val clause_2 = new Clause(Set(B))
    val f1 = new Formula(Set(clause_1, clause_2))
    println(Resolution.satisfiable(f1))
    //println(Resolution.all_resolvents(clause_1, clause_2))
  }
   //Expected: false
  //Can't represent the test case (A&~A)|(B&~B) yet
  def test8() : Unit = {
     
  }
   //Expected: true
  def test9() : Unit = {
    val A = new Literal("A")
    val B = new Literal("B")
    val C = new Literal("C")
    val clause_1 = new Clause(Set(A))
    val clause_2 = new Clause(Set(B))    
    val clause_3 = new Clause(Set(C))
    val f1 = new Formula(Set(clause_1, clause_2, clause_3))
    println(Resolution.satisfiable(f1))
  }
   //Expected: false
  def test10() : Unit = {
    val A = new Literal("A")
    val B = new Literal("B")
    val C = new Literal("C")
    val not_C = C.negation
    val clause_1 = new Clause(Set(A))
    val clause_2 = new Clause(Set(B))    
    val clause_3 = new Clause(Set(C))
    val clause_4 = new Clause(Set(not_C))
    val f1 = new Formula(Set(clause_1, clause_2, clause_3, clause_4))
    println(Resolution.satisfiable(f1))   
  }
  
  //Expected: true
    def test11() : Unit = {
    
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
  //Expected: true
  //Passes test case, but does it do so for the right reasons?
  def test12() : Unit = {
    
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
  def test13() : Unit = {
    val A = new Literal("A")
    val B = new Literal("B")
    
    val clause_1 = new Clause(Set(A, B)) 
    val clause_2 = new Clause(Set(A))

    val f1 = new Formula(Set(clause_1, clause_2))
    println(Resolution.satisfiable(f1))
  }
  
  //Expected: true
  def test14() : Unit = {
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
    def test15() : Unit = {
    val A = new Literal("A")
    val clause_1 = new Clause(Set(A))
    val clause_2 = new Clause(Set(A))

    val f1 = new Formula(Set(clause_1, clause_2))
   // println(Resolution.res(f1))
    println(Resolution.satisfiable(f1))
  }
  
  //Expected: true
  //But for the right reasons?
  def test16() : Unit = {
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