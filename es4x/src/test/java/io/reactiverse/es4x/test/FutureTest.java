package io.reactiverse.es4x.test;

import io.reactiverse.es4x.ECMAEngine;
import io.reactiverse.es4x.Runtime;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class FutureTest {

  private Runtime runtime;

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void initialize() {
    runtime = new ECMAEngine(rule.vertx()).newContext();
  }

  @Test(timeout = 10000)
  public void testJavaThenable(TestContext should) {
    final Async test = should.async();

    // make it available to the script
    runtime.put("should", should);
    runtime.put("test", test);
    // run a small script
    runtime.require("./future/future.js");
  }

  @Test(timeout = 10000)
  public void testCatchFutureAsPromise(TestContext should) {
    final Async test = should.async();

    // make it available to the script
    runtime.put("should", should);
    runtime.put("test", test);
    // run a small script
    runtime.require("./future/future2.js");
  }

  @Test(timeout = 10000)
  public void testIssue184(TestContext should) {
    final Async test = should.async();

    // make it available to the script
    runtime.put("should", should);
    runtime.put("test", test);
    // run a small script
    runtime.require("./future/issue184.js");
  }

  @Test(timeout = 10000)
  public void testJavaFutureAsThenable(TestContext should) {
    final Async test = should.async();

    // make it available to the script
    runtime.put("should", should);
    runtime.put("test", test);
    // run a small script
    runtime.require("./future/future3.js");
  }
}
