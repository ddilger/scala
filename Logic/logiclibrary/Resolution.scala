package logiclibrary


/**
 * @author Daniel
 */
object Resolution {
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

  def res(f: Formula):Formula = {
  //Produce a list of (c1,c2) tuples for the formula
     val clause_pairs : List[(Clause, Clause)] = f.clauses.flatMap(x => f.clauses.map((x, _))).filter(x => x._1 != x._2).toList
  //Map this list into list of sets produced by passing a (c1,c2) pair to all_resolvents()
     val r0 = f.clauses
     val r1 = clause_pairs.map(x => all_resolvents(x._1, x._2))
  //Fold this list of sets into a single set of sets, return this within a formula
     val s = 
       if(r1.length == 0)
         {
           r0
         } 
       else{
         r0 | r1.foldLeft(r1(0)){((m: Set[Clause],n: Set[Clause]) => m ++ n)} | r0 
         }
     val new_f = new Formula(s)
     new_f
  }
  
  //p. 35 of Logic for Computer Scientists
  def satisfiable(f: Formula): Boolean = {
    var F = f.copy
    var G = F.copy
    var i = 0 //Max number of iterations. Purely for testing purposes
    do{
    //  G = F.copy
      F = res(F)
      i = i+1
    }
    while (!F.containsEmpty && !F.equals(G) && i < 3)
    if(F.containsEmpty) //contradiction encountered; unsatisfiable
    {
      println(F)
      false
    }
    else{ //F must equal G
      println(F)
      println(G)
      true
    }
  }
}