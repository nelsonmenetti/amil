package generic.fps.ranking;

import java.util.ArrayList;

import generic.fps.ranking.common.Match;
import generic.fps.ranking.reversematch.SimulateMatchBasedOnLog;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

public class TestReverseMatchAndRanking extends TestCase{


	private static Logger logger = Logger.getLogger(TestReverseMatchAndRanking.class);
	
	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestReverseMatchAndRanking.class );
    }
    
    
    public void resetStaticFields(){
    	SimulateMatchBasedOnLog.currentMatch =null;
    	SimulateMatchBasedOnLog.currentMatchListOfPlayers=null;
    	SimulateMatchBasedOnLog.matches =  new ArrayList<Match>();
    }
    
	public void testSingleMatchProcessing(){
		resetStaticFields();
		String[] args = new String[1];
		args[0]="src/test/resources/logFileToInterpret.log";
		SimulateMatchBasedOnLog.main(args);
		assertEquals(1, SimulateMatchBasedOnLog.matches.size());
		
	}

	public void test2MatchProcessing(){
		resetStaticFields();
		String[] args = new String[1];
		args[0]="src/test/resources/logFileToInterpret2Matches.log";
	    SimulateMatchBasedOnLog.main(args);
		assertEquals(2, SimulateMatchBasedOnLog.matches.size());
	}
    
	public void test2MatchProcessingWithErrorInFile(){
		resetStaticFields();
		String[] args = new String[1];
		args[0]="src/test/resources/logFileToInterpret2MatchesFileHasBrokenInfo.log";
	    try{
		SimulateMatchBasedOnLog.main(args);
	    }catch (RuntimeException e) {
			assertEquals("UNEXPECTED Start of Match inside another match !", e.getMessage());
		}
	}
    
    
}
