package demo.jupiter.extension;

import demo.config.ConfigMapping;
import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.TestRailException;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.Run;
import com.codepine.api.testrail.model.Test;
import org.aeonbits.owner.ConfigCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestRailIntegration {

    private static final Logger LOG = LogManager.getLogger(TestRailIntegration.class);
    private ConfigMapping cfg = ConfigCache.getOrCreate(ConfigMapping.class, System.getProperties());

    public TestRail setUpTestrailInstance() {

        return TestRail.builder(cfg.testRailUrl(), cfg.testRailUserName(), cfg.testRailPassKey()).applicationName("Hal Projects").build();
    }

    //Reads Project Name from ConfigMapping and returns Project Id
    public int returnProject(TestRail testRail) throws TestRailException {
        List<Project> projects = testRail.projects().list().execute();
        return projects.stream().filter(project -> project.getName().equalsIgnoreCase(cfg.testRailProject())).findFirst().map(Project::getId).get();
    }


    //Reads Test Run Name from ConfigMapping and returns Run Id. Project Name
    public int returnRun(TestRail testRail, int projectId) throws TestRailException {
        List<Run> runs = testRail.runs().list(projectId).execute();
        return runs.stream().filter(run -> run.getName().equalsIgnoreCase(cfg.testRailRun())).findFirst().map(Run::getId).get();
    }

    //Method to check if Test case id present in the Test Run
    public boolean checkTestCaseTestRun(int testCaseid, int runId, TestRail testRail) {
        List<Test> testCases = testRail.tests().list(runId).execute();
        ArrayList<Integer> tcid = testCases.stream().map(Test::getCaseId).collect(Collectors.toCollection(ArrayList::new));
        return tcid.contains(testCaseid);

    }


    public void updateTestCaseStatus(TestRail testRail, int runId, int testcaseId, int statusCode) throws TestRailException {
        //Check if TestCase is Present
        if (checkTestCaseTestRun(testcaseId, runId, testRail))
            testRail.results()
                    .addForCase(runId, testcaseId, new Result().setStatusId(statusCode), testRail.resultFields().list().execute())
                    .execute().getStatusId();

    }
}


