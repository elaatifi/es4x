package io.reactiverse.es4x.test;

import io.reactiverse.es4x.ECMAEngine;
import io.reactiverse.es4x.Runtime;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.reactiverse.es4x.test.JS.getMember;
import static org.junit.Assert.assertEquals;

@RunWith(VertxUnitRunner.class)
public class CommonJsUserModulesTest {

  private Runtime runtime;

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void initialize() {
    runtime = new ECMAEngine(rule.vertx()).newContext();
  }

  @Test
  public void shouldFindPackageJsonInModuleFolder() {
    Object packageJson = runtime.require("./lib/other_package");
    assertEquals("cool ranch", getMember(packageJson, "flavor", String.class));
    assertEquals("jar:/lib/other_package/lib/subdir", getMember(packageJson, "subdir", String.class));
  }

  @Test
  public void shouldLoadPackageJsonMainPropertyEvenIfItIsDirectory() {
    Object cheese = runtime.require( "./lib/cheese");
    assertEquals("nacho", getMember(cheese, "flavor", String.class));
  }

  @Test
  public void shouldFindIndexJsInDirectoryIfNoPackageJsonExists() {
    Object packageJson = runtime.require("./lib/my_package");
    assertEquals("Hello!", getMember(packageJson, "data", String.class));
  }
}
