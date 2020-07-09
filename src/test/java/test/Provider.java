package test;

import org.testng.annotations.*;

public class Provider {

  @DataProvider (name = "data-add")
  public Object[][] dataAdd() {
    return new Object[][]{
            {2, 2, 4},
            {0, 0, 0},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, -2} //2147483647
    };
  }

  @DataProvider (name = "data-sub")
  public Object[][] dataSub() {
    return new Object[][] {
            {2, 2, 0},
            {0, 0, 0},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
    };
  }

  @DataProvider (name = "data-mult")
  public Object[][] dataMult() {
    return new Object[][] {
            {-2, 2, -4},
            {0, 0, 0},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 1}
    };
  }

  @DataProvider (name = "data-div")
  public Object[][] dataDiv() {
    return new Object[][] {
            {10, 5, 2},
            {0, 0, 0},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 1}
    };
  }
}
