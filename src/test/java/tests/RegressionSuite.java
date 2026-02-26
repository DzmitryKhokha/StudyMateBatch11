package tests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        LoginTests.class,
        GroupTests.class,
        AnnouncementTests.class
})

public class RegressionSuite {

}
