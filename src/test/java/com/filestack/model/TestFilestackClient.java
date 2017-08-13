package com.filestack.model;

import static com.filestack.util.MockConstants.API_KEY;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Tests {@link FilestackClient FilestackClient} class.
 */
public class TestFilestackClient {

  @Test
  public void testInstantiation() {
    FilestackClient fsClient = new FilestackClient(API_KEY);
    assertNotNull("Unable to create FilestackClient", fsClient);
  }
}
