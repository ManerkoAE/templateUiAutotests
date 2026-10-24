package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.DefaultValue;
import org.aeonbits.owner.Config.Key;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties", "classpath:config.properties"})
public interface TestConfig extends Config {

    @Key("explicit.wait")
    @DefaultValue("10")
    int explicitWait();

    @Key("page.load.timeout")
    @DefaultValue("30")
    int pageLoadTimeout();

    @Key("base.bank.url")
    String baseBankUrl();

    @Key("bank.login")
    String bankLogin();

    @Key("bank.password")
    String bankPassword();

    @Key("bank.email")
    String bankEmail();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("headless")
    @DefaultValue("false")
    boolean headless();

    @Key("hold.browser.open")
    @DefaultValue("false")
    boolean holdBrowserOpen();

    @Key("clear.report.dir")
    @DefaultValue("true")
    boolean clearReportDir();

    TestConfig INSTANCE = ConfigFactory.create(TestConfig.class);
}
