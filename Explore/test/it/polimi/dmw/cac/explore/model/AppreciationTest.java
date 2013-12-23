package it.polimi.dmw.cac.explore.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AppreciationTest extends AppEngineTestCase {

    private Appreciation model = new Appreciation();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
