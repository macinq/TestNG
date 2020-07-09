package test;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.junit.runner.JUnitCore;

import classes.Op;

import java.util.Random;

public class CalcTest {
  static Op c = new Op();

  @BeforeMethod
  public void setUp() {
    c = new Op();
  }

  @AfterMethod
  public void tearDown() {
    c = null;
  }

  @Test(groups = "test-add", dataProvider = "data-add", dataProviderClass = Provider.class/*, enabled = false*/)
  public void testAdd(int a, int b, int ex) {
    assertEquals(c.add(a, b), ex, "add is failed");
  }

  @Test(groups = "test-sub"/*, dataProvider = "data-sub", dataProviderClass = Provider.class*/)
  @Parameters(value = {"a", "b", "ex"})
  public void testSub(int a, int b, int ex) {
    assertEquals(c.sub(a, b), ex, "sub is failed");
  }

  @Test(groups = "test-mult", dataProvider = "data-mult", dataProviderClass = Provider.class)
  public void testMult(int a, int b, int ex) {
    assertEquals(c.mult(a, b), ex, "mult is failed");
  }

  @Test(groups = "test-div", dataProvider = "data-div", dataProviderClass = Provider.class, expectedExceptions = ArithmeticException.class, dependsOnMethods = {"testRandomDiv", "testFuzzingDiv"})
  public void testDiv(int a, int b, int ex) {
    assertEquals(c.div(a, b), ex, "div is failed");
  }

  @Test(groups = {"test-div", "test-random"})
  public void testRandomDiv() {
    boolean flag;

    try {
      int n = 100000;
      Random rand = new Random();
      for (int i = 0; i < n; i++) {
        c.div(rand.nextInt(50) - 25, rand.nextInt(50) - 25);
      }
      flag = false;
    } catch (ArithmeticException e) {
      flag = true;
    }

    assertTrue(flag, "random test is failed");
  }

  @Test(groups = {"test-div", "test-random"}/*, expectedExceptions = NumberFormatException.class*/)
  public void testFuzzingDiv() {
    int ch = 2;
    char[] array = new char[ch];
    int num;
    Random rand = new Random();
    int n = 100000;
    boolean flag;

    try {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < ch; j++) {
          num = (rand.nextInt(127) + 1); //см ASCII
          array[j] = (char) num;
        }
        String str = new String(array);
//        System.out.println(str);
        int s = Integer.parseInt(str);
        c.div(s, s);
      }
      flag = false;
    } catch (Exception e) {
      flag = true;
    }

    assertTrue(flag, "random test is failed");
  }
}