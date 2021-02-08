
package demo.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "file:${user.dir}/config.properties",
        "file:${user.dir}/config/config.properties",
        "file:${user.dir}/user.properties",
        "file:${user.dir}/config/user.properties",
        "classpath:config.properties",
        "classpath:user.properties",
})
public interface ConfigMapping extends Config {

    @Key("psql.host")
    String postgresqlHost();

    @Key("psql.username")
    String psqlUsername();

    @Key("psql.password")
    String psqlPassword();

    @Key("psql.port")
    int psqlPort();

    @Key("psql.dbname")
    String psqlDBName();


    @Key("app.base.url")
    String appBaseUrl();

    @Key("is.remote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("selenide.browser")
    @DefaultValue("chrome")
    String selenideBrowser();

    @Key("remote.driver.url")
    String remoteDriverUrl();

    @DefaultValue("shashank,shashank")
    String user();




    //Test Rail Properties
    @Key("publish.results.to.testrail")
    @DefaultValue("false")
    boolean publishResultsTestrail();

    @Key("testrail.url")
    String testRailUrl();

    @Key("testrail.user")
    String testRailUserName();

    @Key("testrail.passkey")
    String testRailPassKey();

    @Key("testrail.project")
    String testRailProject();

    @Key("testrail.run")
    String testRailRun();


}

