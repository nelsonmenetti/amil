package generic.fps.ranking.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortMapByComparator {

	  public static Map<String, Long> sortByComparator(Map<String, Long> unsortMap, final boolean order)
	    {

	        List<Entry<String, Long>> list = new LinkedList<Entry<String, Long>>(unsortMap.entrySet());

	        Collections.sort(list, new Comparator<Entry<String, Long>>()
	        {
	            public int compare(Entry<String, Long> o1,
	                    Entry<String, Long> o2)
	            {
	                if (order)
	                {
	                    return o1.getValue().compareTo(o2.getValue());
	                }
	                else
	                {
	                    return o2.getValue().compareTo(o1.getValue());

	                }
	            }
	        });

	        Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();
	        for (Entry<String, Long> entry : list)
	        {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        return sortedMap;
	    }
}
