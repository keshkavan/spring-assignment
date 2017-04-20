

// Election  base class 
abstract public class Election {
   boolean done = false;  // when true, break out of processing loop
   
   class Candidate {
	String name;
	String party;
	int voteCount;
	Candidate(String name, String party) {
		this.name = name;
		this.party = party;
		voteCount = 0;
	}
   }
   class Voter {
	String name;
	String party;
	boolean voted;
	Voter(String name, String party) {
		this.name = name;
		this.party = party;
		voted = false;
	}
   }
   
   protected Hashtable candidates = new Hashtable();
   protected Hashtable voters = new Hashtable();
   StringBuffer OfficialRecord = new StringBuffer();
   
   void log(String s) {	
      OfficialRecord.append(s + '\n');
      System.out.println(s);
   }
	
   public static void main(String[] args) {
       // Instantiate an election
       Election e = null;
          if (args.length > 0) {
             if (args[0].compareTo("General") == 0) { 
	        e = new GeneralElection();
	     } else if (args[0].compareTo("Primary") == 0) {
	        e = new PrimaryElection();
	     }
	   } 
       if (e == null) {
          System.out.println("Specify General or Primary");
	  return;
       }
       // Open input file
       FileReader in;
       String filename = "election.dat";
       try {
          in = new FileReader(filename);
       } catch (IOException ex) {
	 e.log("Cannot open " + filename);
	 return;
       } 
       // Processing
       try {
	 e.process(in);
         in.close();
       } catch (IOException ex) {
	 e.log("Something Bad Happened");
       } 
   } // main

/**  Record a vote **/
   void vote(String cand, String voter) throws ElectionException {
      verify(cand, voter);
      Candidate c = getCandidate(cand);
      c.voteCount += 1;
      Voter v = getVoter(voter);
      v.voted = true;
      log(voter + " voted for " + cand);
   }
   
   /** Register a voter **/
   void register(String voter, String party) {
     Voter v = (Voter) voters.get(voter);
     if (v != null) {
        v.party = party;
	log(voter + " affiliation changed to " + party);
     } else {
        voters.put(voter, new Voter(voter, party));
        log("Register voter " + voter+ " as a " + party);
     }
   }
   
   /** Register a candidate **/
   void candidate(String cand, String party) {
     Candidate c = (Candidate) candidates.get(cand);
     if (c != null) {
       c.party = party;
       log(cand + " affiliation changed to " + party);
       return;
     } else {
       candidates.put(cand, new Candidate(cand, party));
       log("Register candidate " + cand + " as a " + party);
     }
   }
   
   /** List all of the candidates, PrimaryElection overrides **/
   void list(String voter) throws ElectionException {
	   Enumeration it = candidates.elements();
	   log("Candidate list for " + voter);
	   int counter = 0;
	   while(it.hasMoreElements()) {
	     Candidate c = (Candidate) it.nextElement();
    	     log("  " + ++counter + c.name);
	   }
   }
   
   /** Report the vote count for each candidate **/
   void tally() throws ElectionException {
	   log("Tally");
	   Enumeration it = candidates.elements();
	   while(it.hasMoreElements()) {
		Candidate c = (Candidate) it.nextElement();
		log("  " + c.name + " (" + c.party + ") " + c.voteCount);
	   }
   }
   
   /** Reset all of the vote counts to 0 and voters as not voted **/
   void reset() {
	   log("Reset");

	   Enumeration ic = candidates.elements();
	   while(ic.hasMoreElements()) {
		Candidate c = (Candidate) ic.nextElement();
		c.voteCount = 0;
	   }
	   
	   Enumeration iv = voters.elements();
	   while(iv.hasMoreElements()) {
		Voter v = (Voter) iv.nextElement();
		v.voted = false;
	   }
	   
   }
   
   void dump() {
	   System.out.println("\n\n----------------Start of Dump-------------------");
	   System.out.println(OfficialRecord);
	   System.out.println("---------------End of Dump------------------------");
   }
   
   void exit(){
	   done = true;
	   log("Exit");
   }
   
   /** Retrieve a voter object
    **/
   Voter getVoter(String voter) throws NotRegisteredException {
      Voter v = (Voter) voters.get(voter);
      if (v == null) {
	 throw new NotRegisteredException(voter);
      }
      return v;
   }
   
   /** Retrieve a candidate object
   **/
   Candidate getCandidate(String name) throws NotACandidateException {
      Candidate c = (Candidate) candidates.get(name);
      if (c == null) {
	 throw new NotACandidateException(name);
      }
      return c;
   }
   
   /** Return true if candidate and voter parties match
    **/
   boolean sameParty(Candidate c, Voter v) {
      return (c.party.compareTo(v.party) == 0);
   }
 	   
}; // Election

