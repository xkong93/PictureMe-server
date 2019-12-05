package com.example.app.Db;

/**
 * @author Xuan Kong
 * @Date 2019-11-26.
 */
public class DbInit {
  public static void main(String[] args) {
    TableInit tableInit = new TableInit();
    TriggerInit triggerInit = new TriggerInit();
    StoredProceduresInit storedProceduresInit = new StoredProceduresInit();
    TestDataInit testDataInit = new TestDataInit();
    testDataInit.insertPhotoTestData();
    testDataInit.insertCategoryTestData();
    testDataInit.insertPhotoCategoryTestData();
    testDataInit.insertTagTestData();
    testDataInit.insertPhotoTagTestData();
  }
}
