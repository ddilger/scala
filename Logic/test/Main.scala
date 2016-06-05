package test

/**
 * @author Daniel
 */
object Main {
  //helper function to find resolvent
  def getL(c1_list: List[Literal], c2_list: List[Literal]):Literal = {
    null
  }
  
  //p. 31 of Logic for Computer Scientists
  def isResolvent(L: Literal, c1: Clause, c2: Clause): Boolean = {
    val not_L = L.negation()
    c2.contains(not_L) && c1.contains(L)
  }
  def resolventforL(L: Literal, c1: Clause, c2: Clause): Clause = {
    val not_L = L.negation()
    if(!isResolvent(L, c1, c2)){
      null //L cannot be used to resolve c1 and c2
    }
    else{
      val r_literals = (c1.literals &~ Set(L)) union (c2.literals &~ Set(not_L))
      new Clause(r_literals)
    }
  } 
  def all_resolvents(c1: Clause, c2: Clause): Set[Clause] = {
    val c1_list = c1.literals.toList
    val c2_list = c2.literals.toList
    val resolvents = for (L <- c1_list if isResolvent(L, c1, c2)) yield resolventforL(L, c1, c2)
    resolvents.toSet
  }
  //For cases when only one clause is needed (inefficient, unfortunately)
  def resolvent(c1: Clause, c2: Clause): Clause = {
    val all = all_resolvents(c1, c2).toList
    if(!all.isEmpty)
    {
      all(0)
    }
    else{
      null
    }
  }
  def res(f: Formula): Formula = {
    val clause_list = f.clauses.toList
    if(clause_list.length < 2){
      f //Only Res0 is possible
    }
    else {//How to ensure you end up with a new element in the set?
        val len = clause_list.length
        val rand = scala.util.Random
        val c1 = clause_list(0)
        val c2 = clause_list(1)
        val r =  resolvent(c1, c2)
        val new_clauses = if(r != null) {f.clauses union Set(r)} else {f.clauses}
        new Formula(new_clauses)
        /*
        val new_clauses = f.clauses union Set(r)
        if (new_clauses != f.clauses){
          new Formula(new_clauses)
        }
        else{
          res(f)
        }
        * 
        */
    }
  }
  //p. 35 of Logic for Computer Scientists
  def satisfiable(f: Formula): Boolean = {
    var F = f.copy
    var G = F.copy
    println(F)
    do{
      G = F.copy
      F = res(F)
    }while (!F.containsEmpty && !F.equals(G))
    if(F.containsEmpty) //contradiction encountered; unsatisfiable
    {
      false
    }
    else{
      println(F)
      println(G)
      true
    }
  }
  
  //Test resolution algorithm
  def main(args: Array[String]): Unit = {
    /*val f = new Formula()
    println(satisfiable(f))*/
    
    /*val p = new Literal("p")
    //val not_p = new Literal(false, "p")
    val un_p = p.negation()
    println(not_p.equals(un_p))*/
    
    val A = new Literal("A")
    val B = new Literal("B")
    val C = new Literal("C")
    val D = new Literal("D")
    val not_D = new Literal(false, "D")
    val E = new Literal("E")
    val not_E = new Literal(false, "E")
    val clause_1 = new Clause(Set(A, B, D.negation()))
    val clause_2 = new Clause(Set(B, D, A.negation()))
    /*println(clause_1.equals(clause_2)) */
    
    /* val all = all_resolvents(clause_1, clause_2)
    print(all) */
   /* 
    val f1 = new Formula(Set(clause_1, clause_2))
    val f2 = f1.copy
    
    println(f1.equals(f2)) */
    
    /*
    val f1 = new Formula(Set(new EmptyClaus))
    println(f1.containsEmpty)
    * */
    
    /*
    val f1 = new Formula(Set(clause_1, clause_2))
    val f2 = res(f1)
    println(f2)
    * 
    */
    
    
    val f1 = new Formula(Set(clause_1, clause_2))
    println(satisfiable(f1))
    
    
    /*
    val clause_3 = new Clause(Set(A, B)) 
    val f1 = new Formula(Set(clause_1, clause_3))
    println(satisfiable(f1))
    * 
    */
  }
}