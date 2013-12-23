package it.polimi.dmw.cac.explore.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CheckInTest extends AppEngineTestCase {

    private CheckIn model = new CheckIn();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
