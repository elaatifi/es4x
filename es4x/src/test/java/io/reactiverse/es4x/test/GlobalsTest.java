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

import java.time.Instant;

@RunWith(VertxUnitRunner.class)
public class GlobalsTest {

  private Runtime runtime;

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void initialize() {
    runtime = new ECMAEngine(rule.vertx()).newContext();
  }

  @Test(timeout = 10000)
  public void testSetTimeout(TestContext ctx) throws Exception {
    final Async async = ctx.async();

    runtime.put("ctx", ctx);
    runtime.put("async", async);

    /// @language=JavaScript
    String script =
      "setTimeout(function () {" +
      "  async.complete();" +
      "}, 1);";


    runtime.eval(script);
  }

  @Test(timeout = 10000)
  public void testSetTimeout0(TestContext ctx) throws Exception {
    final Async async = ctx.async();

    runtime.put("ctx", ctx);
    runtime.put("async", async);

    /// @language=JavaScript
    String script =
      "setTimeout(function () {" +
        "  async.complete();" +
        "}, 0);";


    runtime.eval(script);
  }

  @Test(timeout = 10000)
  public void testSetTimeoutWithParams(TestContext ctx) throws Exception {
    final Async async = ctx.async();

    runtime.put("ctx", ctx);
    runtime.put("async", async);

    /// @language=JavaScript
    String script =
      "setTimeout(function (msg) {" +
        "  ctx.assertEquals('durp!', msg);" +
        "  async.complete();" +
        "}, 1, 'durp!');";


    runtime.eval(script);
  }

  @Test(timeout = 10000)
  public void testDateToInstant(TestContext ctx) throws Exception {
    runtime.put("instant", Instant.now());
    runtime.eval("console.log(Date.fromInstant(instant))");
  }
}
