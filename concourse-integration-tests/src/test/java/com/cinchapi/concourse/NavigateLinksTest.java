package com.cinchapi.concourse;

import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.cinchapi.concourse.lang.Criteria;
import com.cinchapi.concourse.test.ConcourseIntegrationTest;
import com.cinchapi.concourse.thrift.Operator;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class NavigateLinksTest extends ConcourseIntegrationTest {

    @Test
    public void testNavigation() {
        client.insert("{\"name\": \"Jeff\", \"friends\": [\"@2\", \"@3\"]}", 1);
        client.insert("{\"name\": \"Risse\", \"friends\": [\"@3\", \"@1\"]}", 2);
        client.insert("{\"name\": \"Joe\", \"friends\": [\"@2\", \"@4\"]}", 3);
        client.insert("{\"name\": \"Sam\"}", 4);
        Criteria testCriteria = Criteria.where().key("name").operator(Operator.EQUALS).value("Jeff").build();
        Map<Long, Set<Object>> expected = Maps.newHashMap();
        expected.put(2L, Sets.<Object>newHashSet("Risse"));
        expected.put(3L, Sets.<Object>newHashSet("Joe"));
        Map<Long, Set<Object>> actual = client.navigate("friends.name", testCriteria);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testReproA(){
        client.insert("{\"name\": \"Jeff\", \"friends\": [\"@2\", \"@3\"]}", 1);
        client.insert("{\"name\": \"Risse\", \"friends\": [\"@3\", \"@1\"]}", 2);
        client.insert("{\"name\": \"Joe\", \"friends\": [\"@2\", \"@4\"]}", 3);
        client.insert("{\"name\": \"Sam\"}", 4);
        System.out.println(client.navigate("friends.name", Criteria.where().key("name").operator(Operator.EQUALS).value("Jeff").build()));
    }
}